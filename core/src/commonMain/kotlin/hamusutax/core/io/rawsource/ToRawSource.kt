@file:Suppress("UNUSED")
package hamusutax.core.io.rawsource

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun String.rawSource() =
    SystemFileSystem.source(Path(this))

fun RawSource(file: Path) =
    SystemFileSystem.source(file)

fun RawSource(file: String) =
    SystemFileSystem.source(Path(file))
