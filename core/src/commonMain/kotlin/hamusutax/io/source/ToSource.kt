@file:Suppress("unused")
package hamusutax.io.source

import hamusutax.io.buffer.toBuffer
import kotlinx.io.Buffer
import kotlinx.io.RawSource
import kotlinx.io.Source
import kotlinx.io.buffered
import kotlinx.io.bytestring.ByteString
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun String.source() =
    SystemFileSystem.source(Path(this)).buffered()

fun Source(file: Path) =
    SystemFileSystem.source(file).buffered()

fun Source(file: String) =
    SystemFileSystem.source(Path(file)).buffered()

fun ByteArray.source(): Source = toBuffer()

fun ByteString.source(): Source = toBuffer()
