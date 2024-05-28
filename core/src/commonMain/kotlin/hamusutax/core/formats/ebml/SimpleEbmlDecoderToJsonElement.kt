@file:Suppress("UNUSED")
package hamusutax.core.formats.ebml

import hamusutax.core.formats.ebml.EbmlElementType.ASCII_STRING
import hamusutax.core.formats.ebml.EbmlElementType.BINARY
import hamusutax.core.formats.ebml.EbmlElementType.DATE_AND_TIME
import hamusutax.core.formats.ebml.EbmlElementType.FLOAT
import hamusutax.core.formats.ebml.EbmlElementType.MASTER
import hamusutax.core.formats.ebml.EbmlElementType.SIGNED_INTEGER
import hamusutax.core.formats.ebml.EbmlElementType.UNICODE_STRING
import hamusutax.core.formats.ebml.EbmlElementType.UNSIGNED_INTEGER
import hamusutax.core.formats.ebml.define.defineMap
import hamusutax.core.io.buffer.readLongAtLeastOne
import hamusutax.core.io.buffer.readULongAtLeastOne
import hamusutax.core.io.buffer.toBuffer
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlinx.io.IOException
import kotlinx.io.readDouble
import kotlinx.io.readFloat
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

fun SimpleEbmlDecoder.toJsonElement(parser: Json = Json): JsonElement =
    buildJsonObject {
        val elementCounterMap = mutableMapOf<Long, Int>()
        while (true) {
            val (ebmlId, content) = readPairOrNull() ?: break
            val element = defineMap[ebmlId]

            // 防止重名元素覆盖，添加计数后缀
            val elementIndex = elementCounterMap[ebmlId] ?: 0
            elementCounterMap[ebmlId] = elementIndex + 1

            val name = buildString {
                val name = element?.name
                if (name != null) append(name)
                else append("0x${ebmlId.toHexString(HexFormat.UpperCase).trimStart('0')}")

                append("_${elementIndex.toString().padStart(3, '0')}")

                if (name != null) append(" (0x${ebmlId.toHexString(HexFormat.UpperCase).trimStart('0')})")
            }
            val type = element?.type ?: BINARY
            val jsonElement = when (type) {
                MASTER -> {
                    val reader = SimpleEbmlDecoder(content.toBuffer())
                    reader.toJsonElement(parser)
                }
                SIGNED_INTEGER -> JsonPrimitive(content.toBuffer().readLongAtLeastOne())
                UNSIGNED_INTEGER -> JsonPrimitive(content.toBuffer().readULongAtLeastOne())
                FLOAT -> {
                    val buffer = content.toBuffer()
                    when (buffer.size) {
                        0L -> JsonPrimitive(0.0)
                        4L -> JsonPrimitive(buffer.readFloat())
                        8L -> JsonPrimitive(buffer.readDouble())
                        else -> throw IOException("未知 EBML 浮点元素长度：${buffer.size}")
                    }
                    JsonPrimitive(content.toBuffer().readDouble())
                }
                ASCII_STRING -> JsonPrimitive(content.decodeToString())
                UNICODE_STRING -> JsonPrimitive(content.decodeToString())
                DATE_AND_TIME -> {
                    val buffer = content.toBuffer()
                    val nanosecond = when (buffer.size) {
                        0L -> 0L
                        8L -> buffer.readLong()
                        else -> throw IOException("未知 EBML 日期元素长度：${buffer.size}")
                    }
                    val second = nanosecond / 1000_000_000
                    val nanosecondAdjustment = nanosecond % 1000_000_000
                    val datetime = Instant.fromEpochSeconds(978307200 + second, nanosecondAdjustment).toLocalDateTime(TimeZone.UTC)
                    JsonPrimitive(datetimeFormat.format(datetime))
                }
                BINARY -> {
                    val contentString = if (content.size > 32) {
                        buildString {
                            append(content.sliceArray(0..<8).toHexString(HexFormat.UpperCase))
                            append("...")
                            append(content.sliceArray(content.size - 8..<content.size).toHexString(HexFormat.UpperCase))
                        }
                    } else content.toHexString(HexFormat.UpperCase)
                    JsonPrimitive("<hex type='${type.abbr}' size='${content.size}'>$contentString</hex>")
                }
            }
            put(name, jsonElement)
        }
    }

private val datetimeFormat = LocalDateTime.Format {
    year()
    char('-')
    monthNumber()
    char('-')
    dayOfMonth()
    char('T')
    hour()
    char(':')
    minute()
    char(':')
    second()
    char('.')
    secondFraction(9)
}
