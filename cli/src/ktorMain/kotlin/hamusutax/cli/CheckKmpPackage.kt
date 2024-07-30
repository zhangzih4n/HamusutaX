package hamusutax.cli

import hamusutax.serialization.json.JsonEncodeDefaultsPrettyPrint
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString

suspend fun HttpClient.queryKmpPackage(
    gav: String,
    repos: Map<String, (group: String, module: String, version: String) -> Boolean> = defaultRepos,
    deprecatedTarget: Boolean = false
): String {
    val gavSplit = gav.split(":")
    return queryKmpPackage(gavSplit[0], gavSplit[1], gavSplit[2], repos, deprecatedTarget)
}

suspend fun HttpClient.queryKmpPackage(
    group: String,
    module: String,
    version: String,
    repos: Map<String, (group: String, module: String, version: String) -> Boolean> = defaultRepos,
    deprecatedTarget: Boolean = false
) = coroutineScope {
    val mutex = Mutex()
    val result = mutableMapOf<String, Boolean>()
    val queryPlatforms =
        if (deprecatedTarget) platforms else platforms.filter { !it.isDeprecated }

    for ((repoUrl, predicate) in repos) {
        if (!predicate(group, module, version))
            continue

        val url = queryUrl(repoUrl, group, module, version)
        val mainResponse = get(url)

        if (mainResponse.status.value == 200) result["main"] = true
        else continue

        queryPlatforms.map { (suffix, isDeprecated, isExperimental) ->
            launch {
                val url = queryUrl(repoUrl, group, "$module-${suffix}", version)
                val response = get(url)
                mutex.withLock {
                    result[suffix] = response.status.value == 200
                }
            }
        }.joinAll()
        break
    }

    JsonEncodeDefaultsPrettyPrint.encodeToString(result.toList().sortedBy { it.first }.toMap())
}

private fun queryUrl(repoUrl: String, group: String, module: String, version: String): String {
    val repoUrl = repoUrl.trimEnd('/')
    val group = group.replace('.', '/')
    val url = "$repoUrl/$group/$module/$version/$module-$version.pom".also { println(it) }
    return url
}

private data class Platform(
    val suffix: String,
    val isDeprecated: Boolean = false,
    val isExperimental: Boolean = false
)

private val platforms = listOf(
    Platform("androidnativearm32"),
    Platform("androidnativearm64"),
    Platform("androidnativex64"),
    Platform("androidnativex86"),
    Platform("iosarm32", true),
    Platform("iosarm64"),
    Platform("iossimulatorarm64"),
    Platform("iosx64"),
    Platform("js"),
    Platform("jvm"),
    Platform("linuxarm32hfp", true),
    Platform("linuxarm64"),
    Platform("linuxx64"),
    Platform("macosarm64"),
    Platform("macosx64"),
    Platform("mingwx64"),
    Platform("mingwx86", true),
    Platform("tvosarm64"),
    Platform("tvossimulatorarm64"),
    Platform("tvosx64"),
    Platform("wasm32", true),
    Platform("wasm-js"),
    Platform("wasm-wasi", isExperimental = true),
    Platform("watchosarm32"),
    Platform("watchosarm64"),
    Platform("watchosdevicearm64"),
    Platform("watchossimulatorarm64"),
    Platform("watchosx64"),
    Platform("watchosx86", true),
)

private fun String.isGroupOrSubgroup(group: String) =
    group == this || group.startsWith("$group.")

val defaultRepos: Map<String, (group: String, module: String, version: String) -> Boolean> = mapOf(
    "https://repo.maven.apache.org/maven2/" to { _, _, _ -> true },
    "https://maven.google.com/" to { group: String, module: String, version: String ->
        group.isGroupOrSubgroup("androidx") || group.isGroupOrSubgroup("com.android") || group.isGroupOrSubgroup("com.google")
    },
    "https://jitpack.io/" to { group: String, module: String, version: String ->
        group.isGroupOrSubgroup("com.github")
    },
    "https://s01.oss.sonatype.org/content/repositories/releases/" to { _, _, _ -> true },
    "https://s01.oss.sonatype.org/content/repositories/snapshots/" to { _, _, _ -> true },
    "https://maven.pkg.jetbrains.space/public/p/ktor/eap/" to { group: String, module: String, version: String ->
        group.isGroupOrSubgroup("io.ktor")
    }
)
