package hamusutax.formats.ebml

import hamusutax.number.setBit
import kotlinx.io.Buffer
import kotlinx.io.readByteArray
import kotlinx.serialization.SerializationException

fun Byte.toVInt() =
    toLong().toVInt()

fun Short.toVInt() =
    toLong().toVInt()

fun Int.toVInt() =
    toLong().toVInt()

fun Long.toVInt(): ByteArray {
    val buffer = Buffer()
    when {
        this < 0x7F -> {
            buffer.writeLong(setBit(8))
            val bytes = buffer.readByteArray()
            buffer.writeByte(bytes[7])
        }
        this < 0x3FFF -> {
            buffer.writeLong(setBit(15))
            val bytes = buffer.readByteArray()
            buffer.write(bytes.slice(6..7).toByteArray())
        }
        this < 0x1FFFFF -> {
            buffer.writeLong(setBit(22))
            val bytes = buffer.readByteArray()
            buffer.write(bytes.slice(5..7).toByteArray())
        }
        this < 0xFFFFFFF -> {
            buffer.writeLong(setBit(29))
            val bytes = buffer.readByteArray()
            buffer.write(bytes.slice(4..7).toByteArray())
        }
        this < 0x7FFFFFFFF -> {
            buffer.writeLong(setBit(36))
            val bytes = buffer.readByteArray()
            buffer.write(bytes.slice(3..7).toByteArray())
        }
        this < 0x3FFFFFFFFFF -> {
            buffer.writeLong(setBit(43))
            val bytes = buffer.readByteArray()
            buffer.write(bytes.slice(2..7).toByteArray())
        }
        this < 0x1FFFFFFFFFFFF -> {
            buffer.writeLong(setBit(50))
            val bytes = buffer.readByteArray()
            buffer.write(bytes.slice(1..7).toByteArray())
        }
        this < 0xFFFFFFFFFFFFFF -> {
            buffer.writeLong(setBit(57))
            val bytes = buffer.readByteArray()
            buffer.write(bytes.slice(0..7).toByteArray())
        }
        else -> throw Exception("整数过大！当前值为 $this，允许的值范围为 0..(2 ^ 56 - 2)，即 72057594037927934 (0xFFFFFFFFFFFFFE)")
    }
    return buffer.readByteArray()
}

internal fun List<Annotation>.getEbmlId() =
    getEbmlIdOrNull() ?: throw SerializationException("未找到 EbmlID 注解！")

internal fun List<Annotation>.getEbmlIdOrNull() =
    firstOrNull { it is EbmlID } as EbmlID?
