@file:Suppress("UNUSED")
package hamusutax.core.okio

import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

fun Source(file: Path) =
    FileSystem.SYSTEM.source(file)

fun Source(file: String) =
    Source(file.toPath())
