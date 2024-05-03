@file:Suppress("UNUSED")
package hamusutax.io

import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import java.io.File
import kotlin.io.path.pathString

fun File.rawSource() =
    SystemFileSystem.source(Path(toPath().pathString))

fun java.nio.file.Path.rawSource() =
    SystemFileSystem.source(Path(pathString))

fun java.nio.file.Path.source() =
    rawSource().buffered()

fun File.source() =
    rawSource().buffered()

val Path.javaFile: File
    get() = File(toString())

val Path.javaPath: java.nio.file.Path
    get() = javaFile.toPath()
