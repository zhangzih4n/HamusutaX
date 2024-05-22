@file:Suppress("UNUSED")
package hamusutax.jvm.io.file

import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import java.io.File
import kotlin.io.path.pathString

fun File.rawSource() =
    SystemFileSystem.source(Path(toPath().pathString))

fun File.rawSink() =
    SystemFileSystem.sink(Path(toPath().pathString))

fun File.source() =
    rawSource().buffered()

fun File.sink() =
    rawSink().buffered()

fun File.readFirstLine(): String =
    bufferedReader().readLine()
