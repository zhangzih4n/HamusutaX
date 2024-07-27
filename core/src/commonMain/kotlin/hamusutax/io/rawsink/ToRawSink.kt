@file:Suppress("unused")
package hamusutax.io.rawsink

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun String.rawSink() =
    SystemFileSystem.sink(Path(this))

fun RawSink(file: Path) =
    SystemFileSystem.sink(file)

fun RawSink(file: String) =
    SystemFileSystem.sink(Path(file))
