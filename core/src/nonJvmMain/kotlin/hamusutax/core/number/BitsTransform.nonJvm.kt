@file:Suppress("UNUSED")
package hamusutax.core.number

import kotlinx.io.Buffer
import kotlinx.io.readDouble
import kotlinx.io.writeDouble

actual fun Long.bitsToDouble(): Double =
    Buffer().apply { writeLong(this@bitsToDouble) }.readDouble()

actual fun Double.bitsToLong(): Long =
    Buffer().apply { writeDouble(this@bitsToLong) }.readLong()

actual fun Int.twosComplement(trimStart: Boolean) =
    if (this >= 0) {
        toString(2).let {
            if (trimStart) it else it.padStart(32, '0')
        }
    } else (-this - 1).toString(2)
        .padStart(32, '0')
        .map { if (it == '0') 1 else 0 }
        .joinToString("")
