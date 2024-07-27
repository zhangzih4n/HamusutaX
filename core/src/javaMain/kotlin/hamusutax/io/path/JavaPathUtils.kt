@file:Suppress("unused")
package hamusutax.io.path

import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import java.nio.file.Path as JavaPath
import kotlin.io.path.bufferedReader
import kotlin.io.path.pathString

fun JavaPath.rawSource() =
    SystemFileSystem.source(Path(pathString))

fun JavaPath.rawSink() =
    SystemFileSystem.sink(Path(pathString))

fun JavaPath.source() =
    SystemFileSystem.source(Path(pathString)).buffered()

fun JavaPath.sink() =
    SystemFileSystem.sink(Path(pathString)).buffered()

fun JavaPath.readFirstLine(): String =
    bufferedReader().readLine()
