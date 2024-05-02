@file:Suppress("UNUSED")
package hamusutax.io

import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.files.SystemPathSeparator

fun Path.rawSource() =
    SystemFileSystem.source(this)

fun Path.rawSink() =
    SystemFileSystem.sink(this)

fun Path.source() =
    rawSource().buffered()

fun Path.sink() =
    rawSource().buffered()

fun Path.metadataOrNull() =
    SystemFileSystem.metadataOrNull(this)

operator fun Path.div(other: Path) =
    Path("$this$SystemPathSeparator$other")

operator fun Path.div(other: String) =
    Path("$this$SystemPathSeparator$other")

operator fun String.div(other: Path) =
    Path("$this$SystemPathSeparator$other")
