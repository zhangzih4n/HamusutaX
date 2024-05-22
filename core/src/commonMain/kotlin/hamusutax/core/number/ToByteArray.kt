@file:Suppress("UNUSED")
package hamusutax.core.number

import kotlinx.io.Buffer
import kotlinx.io.readByteArray
import kotlinx.io.writeIntLe
import kotlinx.io.writeLongLe
import kotlinx.io.writeShortLe
import kotlinx.io.writeUByte
import kotlinx.io.writeUInt
import kotlinx.io.writeUIntLe
import kotlinx.io.writeULong
import kotlinx.io.writeULongLe
import kotlinx.io.writeUShort
import kotlinx.io.writeUShortLe

fun Byte.toByteArray() =
    Buffer().apply { writeByte(this@toByteArray) }.readByteArray()

fun Short.toByteArray() =
    Buffer().apply { writeShort(this@toByteArray) }.readByteArray()

fun Short.toByteArrayLe() =
    Buffer().apply { writeShortLe(this@toByteArrayLe) }.readByteArray()

fun Int.toByteArray() =
    Buffer().apply { writeInt(this@toByteArray) }.readByteArray()

fun Int.toByteArrayLe() =
    Buffer().apply { writeIntLe(this@toByteArrayLe) }.readByteArray()

fun Long.toByteArray() =
    Buffer().apply { writeLong(this@toByteArray) }.readByteArray()

fun Long.toByteArrayLe() =
    Buffer().apply { writeLongLe(this@toByteArrayLe) }.readByteArray()

fun UByte.toByteArray() =
    Buffer().apply { writeUByte(this@toByteArray) }.readByteArray()

fun UShort.toByteArray() =
    Buffer().apply { writeUShort(this@toByteArray) }.readByteArray()

fun UShort.toByteArrayLe() =
    Buffer().apply { writeUShortLe(this@toByteArrayLe) }.readByteArray()

fun UInt.toByteArray() =
    Buffer().apply { writeUInt(this@toByteArray) }.readByteArray()

fun UInt.toByteArrayLe() =
    Buffer().apply { writeUIntLe(this@toByteArrayLe) }.readByteArray()

fun ULong.toByteArray() =
    Buffer().apply { writeULong(this@toByteArray) }.readByteArray()

fun ULong.toByteArrayLe() =
    Buffer().apply { writeULongLe(this@toByteArrayLe) }.readByteArray()
