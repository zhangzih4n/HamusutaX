@file:Suppress("UNUSED")
package hamusutax.core.io.sink

import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun String.sink() =
    SystemFileSystem.sink(Path(this)).buffered()

fun Sink(file: Path) =
    SystemFileSystem.sink(file).buffered()

fun Sink(file: String) =
    SystemFileSystem.sink(Path(file)).buffered()
