@file:Suppress("unused")
package hamusutax.io.path

import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.readByteArray
import kotlinx.io.readString
import kotlinx.io.writeString

fun Path.source() =
    rawSource().buffered()

fun Path.sink() =
    rawSink().buffered()

fun Path.readByteArray() =
    source().readByteArray()

fun Path.writeByteArray(array: ByteArray) {
    sink().apply {
        write(array)
        flush()
    }
}

fun Path.readText() =
    source().readString()

fun Path.writeText(text: CharSequence) =
    writeByteArray(text.toString().encodeToByteArray())

fun Path.readLines() =
    readText().lines()

private const val LB: Byte = 10
fun Path.writeLines(lines: Iterable<CharSequence>) {
    sink().apply {
        lines.forEach { line ->
            writeString(line.toString())
            writeByte(LB)
        }
        flush()
    }
}

fun Path.writeLines(lines: Sequence<CharSequence>) =
    writeLines(lines.asIterable())

val Path.nameWithoutExtension
    get() = name.substringBeforeLast('.')

val Path.extension
    get() = name.substringAfterLast('.', "")

val Path.root: Path
    get() {
        var p = this
        while (true) {
            p = p.parent ?: return p
        }
    }
