@file:Suppress("unused")
package hamusutax.formats.ebml.simple

import hamusutax.formats.ebml.simple.IntegerType.BYTES_LENGTH
import hamusutax.formats.ebml.simple.IntegerType.ID
import hamusutax.io.buffer.readLongAtLeastOne
import hamusutax.io.source.readAtMost
import hamusutax.number.BitAction.ZERO
import hamusutax.number.setHighestOneBit
import kotlinx.io.Buffer
import kotlinx.io.EOFException
import kotlinx.io.IOException
import kotlinx.io.Source

class SimpleEbmlDecoder(private val source: Source) {
    fun readPair() =
        readPairOrNull() ?: throw EOFException()

    fun readPairOrNull(): Pair<Long, ByteArray>? {
        if (!source.request(1))
            return null
        val id = readId()
        val length = readInteger()
        source.require(length)
        return id to source.readAtMost(length)
    }

    fun readAll(): List<Pair<Long, ByteArray>> {
        val pairs = mutableListOf<Pair<Long, ByteArray>>()
        while (true) {
            val pair = readPairOrNull() ?: break
            pairs.add(pair)
        }
        return pairs.toList()
    }

    fun readId() =
        readInteger(ID)

    /**
     * @param type 整数数据类型。作为字节序列长度时会移除最高位的 1。作为 ID 时无需移除
     */
    fun readInteger(type: IntegerType = BYTES_LENGTH): Long {
        var isReadingLength = true
        var remainBytes = 1
        val buffer = Buffer()

        while (remainBytes > 0) {
            remainBytes--
            val byte = source.readByte()
            if (isReadingLength) {
                if (byte == 0.toByte()) {
                    remainBytes += 8
                } else {
                    remainBytes += byte.countLeadingZeroBits()
                    isReadingLength = false
                    if (type == BYTES_LENGTH) {
                        buffer.writeByte(byte.setHighestOneBit(ZERO))
                        continue
                    }
                }
            }
            buffer.writeByte(byte)
        }
        if (buffer.size > 8)
            throw IOException("EBML 字节长度大于 Long.MAX_VALUE！")

        return buffer.readLongAtLeastOne()
    }
}

enum class IntegerType {
    ID, BYTES_LENGTH
}
