@file:Suppress("UNUSED")
package hamusutax.jvm.io.path

import kotlinx.io.buffered
import kotlinx.io.files.SystemFileSystem
import java.nio.file.Path
import kotlin.io.path.bufferedReader
import kotlin.io.path.pathString

fun Path.rawSource() =
    SystemFileSystem.source(kotlinx.io.files.Path(pathString))

fun Path.rawSink() =
    SystemFileSystem.source(kotlinx.io.files.Path(pathString))

fun Path.source() =
    SystemFileSystem.source(kotlinx.io.files.Path(pathString)).buffered()

fun Path.sink() =
    SystemFileSystem.source(kotlinx.io.files.Path(pathString)).buffered()

fun Path.readFirstLine(): String =
    bufferedReader().readLine()
