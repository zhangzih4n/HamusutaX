package hamusutax.core.formats.bencode

import kotlinx.io.Buffer
import kotlinx.io.IOException
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.io.readString
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class SimpleBencodeDecoder(val source: Source) {
    fun readByteArray(): ByteArray {
        val buffer = Buffer()
        while (true) {
            val byte = source.readByte()
            when (byte.toInt().toChar()) {
                in '0'..'9' -> buffer.writeByte(byte)
                ':' -> break
                else -> throw IOException("解析字符串长度时遇到预期外字符：\"${byte.toInt().toChar()}\"(${byte.toInt()})")
            }
        }
        val length = buffer.readString().toLong()
        source.require(length)
        return source.readByteArray(length.toInt())
    }

    fun readInteger(): String {
        require(source.readByte().toInt().toChar() == 'i')
        val buffer = Buffer()

        var byte = source.readByte()
        if (byte.toInt().toChar() in (('0'..'9') + '-')) buffer.writeByte(byte)
        else throw IOException("解析整数时遇到预期外字符：\"${byte.toInt().toChar()}\"(${byte.toInt()})")

        while (true) {
            byte = source.readByte()
            when (byte.toInt().toChar()) {
                in '0'..'9' -> buffer.writeByte(byte)
                'e' -> break
                else -> throw IOException("解析整数时遇到预期外字符：\"${byte.toInt().toChar()}\"(${byte.toInt()})")
            }
        }

        return buffer.readString()
    }

    fun readList(): BencodeList {
        require(source.readByte().toInt().toChar() == 'l')

        val list = buildList {
            while (true) {
                val byte = source.peek().readByte()
                val element = when (byte.toInt().toChar()) {
                    in '0'..'9' -> BencodeByteString(readByteArray())
                    'i' -> BencodeInteger(readInteger())
                    'l' -> BencodeList(readList())
                    'd' -> BencodeDictionary(readDict())
                    'e' -> {
                        source.readByte()
                        break
                    }
                    else -> throw IOException("解析列表时遇到预期外字符：\"${byte.toInt().toChar()}\"(${byte.toInt()})")
                }
                add(element)
            }
        }
        return BencodeList(list)
    }

    fun readDict(): BencodeDictionary {
        require(source.readByte().toInt().toChar() == 'd')

        val map = buildMap {
            while (true) {
                if (source.peek().readByte().toInt().toChar() == 'e') {
                    source.readByte()
                    break
                } else {
                    val key = BencodeByteString(readByteArray())
                    val byte = source.peek().readByte()
                    val value = when (byte.toInt().toChar()) {
                        in '0'..'9' -> BencodeByteString(readByteArray())
                        'i' -> BencodeInteger(readInteger())
                        'l' -> BencodeList(readList())
                        'd' -> BencodeDictionary(readDict())
                        else -> throw IOException("解析字典时遇到预期外字符：\"${byte.toInt().toChar()}\"(${byte.toInt()})")
                    }
                    put(key, value)
                }
            }
        }
        return BencodeDictionary(map)
    }
}
