@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun Path.toSink() =
    SystemFileSystem.sink(this)

fun String.toSink() =
    SystemFileSystem.sink(Path(this))

fun Sink(file: Path) =
    SystemFileSystem.sink(file)

fun Sink(file: String) =
    SystemFileSystem.sink(Path(file))
