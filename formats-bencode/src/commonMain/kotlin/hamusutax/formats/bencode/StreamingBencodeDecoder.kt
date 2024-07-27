package hamusutax.formats.bencode

import hamusutax.io.source.peekByteAsChar
import hamusutax.io.source.readByteAsChar
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.io.readString
import kotlinx.io.writeString
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.StructureKind.*
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.CompositeDecoder.Companion.UNKNOWN_NAME

internal class StreamingBencodeDecoder(
    override val bencode: Bencode,
    override val source: Source
) : SourceBencodeDecoder(bencode, source) {

    private val buffer = Buffer()
    private val stack = mutableListOf<Int>()

    override fun decodeByteArray(): ByteArray {
        while (true) {
            val byte = source.readByte()
            when (byte.toInt().toChar()) {
                in INTEGER_CHARSET -> buffer.writeByte(byte)
                STRING_SEPARATOR_CHAR -> break
                else -> throw SerializationException("解析字符串长度时遇到预期外字符：\"${byte.toInt().toChar()}\"(${byte.toInt()})")
            }
        }
        val length = buffer.readString().toLong()
        source.require(length)
        return source.readByteArray(length.toInt())
    }

    override fun decodeLong(): Long {
        require(source.readByteAsChar() == INTEGER_START_CHAR)
        val firstChar = source.readByteAsChar()
        if (firstChar in INTEGER_FIRST_CHAR_CHARSET)
            buffer.writeString(firstChar.toString())
        else
            throw SerializationException("解析整数时遇到预期外字符：\"$firstChar\"(${firstChar.code})")

        while (true) {
            when (val char = source.readByteAsChar()) {
                in INTEGER_CHARSET -> buffer.writeString(char.toString())
                END_CHAR -> break
                else -> throw SerializationException("解析整数时遇到预期外字符：\"$char\"(${char.code})")
            }
        }
        return buffer.readString().toLong()
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (source.peekByteAsChar() == END_CHAR)
            return DECODE_DONE

        return when (descriptor.kind) {
            LIST, MAP -> stack.removeLast().also { stack.add(it + 1) }
            CLASS, OBJECT -> {
                var index: Int
                while (true) {
                    if (source.peekByteAsChar() == END_CHAR)
                        return DECODE_DONE

                    index = descriptor.getElementIndex(decodeString())
                    when (index) {
                        UNKNOWN_NAME -> decodeBencodeElement()
                        else -> break
                    }
                }
                index
            }
            else -> throw SerializationException("未知结构体类型：${descriptor.kind}！")
        }
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        val char = source.readByteAsChar()
        when (descriptor.kind as StructureKind) {
            LIST -> require(char == LIST_START_CHAR) { "未知结构体起始字符：$char" }
            MAP, CLASS, OBJECT -> require(char == DICTIONARY_START_CHAR) { "未知结构体起始字符：$char" }
        }
        stack.add(0)
        return this
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        require(source.readByteAsChar() == END_CHAR)
        stack.removeLast()
    }
}
