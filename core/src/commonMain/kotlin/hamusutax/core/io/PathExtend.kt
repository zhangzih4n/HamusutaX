@file:Suppress("UNUSED")
package hamusutax.core.io

import hamusutax.core.io.PathWalkOption.BREADTH_FIRST
import kotlinx.io.buffered
import kotlinx.io.files.FileNotFoundException
import kotlinx.io.files.FileSystem
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.files.SystemPathSeparator

fun Path.resolve() =
    SystemFileSystem.resolve(this)

fun Path.exists() =
    SystemFileSystem.exists(this)

fun Path.list() =
    SystemFileSystem.list(this)

fun Path.createDirectories(mustCreate: Boolean = false) =
    SystemFileSystem.createDirectories(this, mustCreate)

fun Path.atomicMove(destination: Path) =
    SystemFileSystem.atomicMove(this, destination)

fun Path.delete(mustExist: Boolean = true) =
    SystemFileSystem.delete(this, mustExist)

fun FileSystem.deleteRecursively(path: Path, mustExist: Boolean = true) {
    if (mustExist && !path.exists()) throw FileNotFoundException("$path")

    val queue = mutableListOf(path)
    while (queue.isNotEmpty()) {
        val first = queue.first()
        metadataOrNull(first)?.let {
            if (it.isDirectory) {
                val list = list(first)
                if (list.isEmpty()) {
                    delete(first)
                    queue.removeFirst()
                } else queue.addAll(0, list)
            }
            if (it.isRegularFile) {
                delete(first)
                queue.removeFirst()
            }
        }
    }
}

fun Path.deleteRecursively(mustExist: Boolean = true) =
    SystemFileSystem.deleteRecursively(this, mustExist)

fun Path.metadataOrNull() =
    SystemFileSystem.metadataOrNull(this)

val Path.size
    get() = SystemFileSystem.metadataOrNull(this)?.size

val Path.isRegularFile
    get() = SystemFileSystem.metadataOrNull(this)?.isRegularFile

val Path.isDirectory
    get() = SystemFileSystem.metadataOrNull(this)?.isDirectory

fun Path.rawSource() =
    SystemFileSystem.source(this)

fun Path.rawSink() =
    SystemFileSystem.sink(this)

fun Path.source() =
    rawSource().buffered()

fun Path.sink() =
    rawSink().buffered()

operator fun Path.div(other: Path) =
    Path("$this$SystemPathSeparator$other")

operator fun Path.div(other: String) =
    Path("$this$SystemPathSeparator$other")

operator fun String.div(other: Path) =
    Path("$this$SystemPathSeparator$other")

fun Path.writeBytes(array: ByteArray) {
    val buffer = rawSink().buffered()
    buffer.write(array)
    buffer.flush()
}

fun Path.writeText(text: CharSequence) =
    writeBytes(text.toString().encodeToByteArray())

fun Path.writeLines(lines: Iterable<CharSequence>) {
    val buffer = rawSink().buffered()
    lines.forEach { line ->
        buffer.write(line.toString().encodeToByteArray())
        buffer.writeByte('\n'.code.toByte())
    }
    buffer.flush()
}

fun Path.writeLines(lines: Sequence<CharSequence>) {
    val buffer = rawSink().buffered()
    lines.forEach { line ->
        buffer.write(line.toString().encodeToByteArray())
        buffer.writeByte('\n'.code.toByte())
    }
    buffer.flush()
}

/**
 * 当前参数只支持 [PathWalkOption.BREADTH_FIRST]
 */
fun FileSystem.walk(path: Path, vararg options: PathWalkOption): Sequence<Path> = sequence {
    if (BREADTH_FIRST in options) {
        // 广度优先
        val queue = mutableListOf(path)
        while (queue.isNotEmpty()) {
            val first = queue.removeFirst()
            metadataOrNull(first)?.let {
                if (it.isDirectory)
                    queue.addAll(list(first))
                if (it.isRegularFile)
                    yield(first)
            }
        }
    } else {
        // 深度优先
        val queue = mutableListOf(path)
        while (queue.isNotEmpty()) {
            val first = queue.removeFirst()
            metadataOrNull(first)?.let {
                if (it.isDirectory)
                    queue.addAll(0, list(first))
                if (it.isRegularFile)
                    yield(first)
            }
        }
    }
}

fun Path.walk(vararg options: PathWalkOption) =
    SystemFileSystem.walk(this, *options)

enum class PathWalkOption {
    BREADTH_FIRST
}
