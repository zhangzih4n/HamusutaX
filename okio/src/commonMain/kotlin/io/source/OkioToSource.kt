package io.source

import io.buffer.toBuffer
import kotlinx.io.Source
import okio.ByteString

fun ByteString.source() =
    toBuffer() as Source
