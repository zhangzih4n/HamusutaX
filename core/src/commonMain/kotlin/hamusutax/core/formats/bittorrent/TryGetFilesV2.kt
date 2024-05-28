package hamusutax.core.formats.bittorrent

import hamusutax.core.formats.bencode.BencodeDictionary
import hamusutax.core.formats.bencode.toJsonElement
import hamusutax.core.io.path.div
import kotlinx.io.files.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

@Serializable
data class V2File(
    val length: Long,
    @SerialName("pieces root") val piecesRoot: String
)

fun BencodeDictionary.filesV2(): Map<Path, V2File>? {
    val result = mutableMapOf<Path, V2File>()

    val fileTree = toJsonElement().jsonObject["info"]?.jsonObject?.get("file tree")?.jsonObject ?: return null
    val queue = mutableListOf(Path("/") to fileTree)

    while (queue.isNotEmpty()) {
        val (parentPath, jsonObject) = queue.removeFirst()
        jsonObject.toList().forEach { (path, value) ->
            if (value.isDictionary()) {
                queue.add(parentPath / path to value.jsonObject)
            } else {
                val file = Json.decodeFromJsonElement<V2File>(value.jsonObject[""]!!)
                result[parentPath / path] = file
            }
        }
    }

    return result
}

private fun JsonElement.isDictionary() =
    jsonObject[""] == null
