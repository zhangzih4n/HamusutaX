@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun Path.toSource() =
    SystemFileSystem.source(this)

fun String.toSource() =
    SystemFileSystem.source(Path(this))

fun Source(file: Path) =
    SystemFileSystem.source(file)

fun Source(file: String) =
    SystemFileSystem.source(Path(file))
