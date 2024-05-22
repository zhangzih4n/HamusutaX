@file:Suppress("UNUSED")
package hamusutax.core.io.source

import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun String.source() =
    SystemFileSystem.source(Path(this)).buffered()

fun Source(file: Path) =
    SystemFileSystem.source(file).buffered()

fun Source(file: String) =
    SystemFileSystem.source(Path(file)).buffered()
