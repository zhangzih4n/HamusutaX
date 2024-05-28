package hamusutax.core.formats.bencode

import hamusutax.core.serialization.json.toJsonElement
import hamusutax.core.serialization.json.toJsonElementForce
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonUnquotedLiteral
import kotlin.text.HexFormat.Companion

private fun BencodeElement.error(element: String): Nothing =
    throw IllegalArgumentException("Element ${this::class} is not a $element")

sealed class BencodeElement {
    val BencodeInteger get() = this as? BencodeInteger
}

class BencodeByteString(
    private val value: ByteArray
): BencodeElement() {
    constructor(value: String): this(value.encodeToByteArray())

    val string get() = value.decodeToString()
    fun stringOrHex(
        prefix: String = "<hex>",
        postfix: String = "</hex>",
        hexFormat: HexFormat = HexFormat.UpperCase
    ) = runCatching { value.decodeToString().also { require('�' !in it) } }
        .getOrNull() ?: (prefix + value.toHexString(hexFormat) + postfix)
}

class BencodeInteger(
    val value: String
): BencodeElement() {
    val byte get() = value.toByte()
    val byteOrNull = value.toByteOrNull()
    val short get() = value.toShort()
    val shortOrNull = value.toShortOrNull()
    val int get() = value.toInt()
    val intOrNull = value.toIntOrNull()
    val long get() = value.toLong()
    val longOrNull get() = value.toLongOrNull()
    val uByte get() = value.toUByte()
    val uByteOrNull = value.toUByteOrNull()
    val uShort get() = value.toUShort()
    val uShortOrNull = value.toUShortOrNull()
    val uInt get() = value.toUInt()
    val uIntOrNull = value.toUIntOrNull()
    val uLong get() = value.toULong()
    val uLongOrNull get() = value.toULongOrNull()
}

fun BencodeInteger(value: Byte) = BencodeInteger(value.toString())
fun BencodeInteger(value: Short) = BencodeInteger(value.toString())
fun BencodeInteger(value: Int) = BencodeInteger(value.toString())
fun BencodeInteger(value: Long) = BencodeInteger(value.toString())
fun BencodeInteger(value: UByte) = BencodeInteger(value.toString())
fun BencodeInteger(value: UShort) = BencodeInteger(value.toString())
fun BencodeInteger(value: UInt) = BencodeInteger(value.toString())
fun BencodeInteger(value: ULong) = BencodeInteger(value.toString())

class BencodeList(
    private val value: List<BencodeElement>
): BencodeElement(), List<BencodeElement> by value

class BencodeDictionary(
    private val value: Map<BencodeByteString, BencodeElement>
): BencodeElement(), Map<BencodeByteString, BencodeElement> by value

fun BencodeElement.toJsonElement() =
    toJsonElementForce(
        keyAction = { element ->
            if (element !is BencodeByteString) throw SerializationException()
            element.stringOrHex()
        },
        valueAction = { element ->
            when (element) {
                is BencodeByteString -> JsonPrimitive(element.stringOrHex())
                is BencodeInteger -> JsonUnquotedLiteral(element.value)
                is BencodeList -> JsonArray(element.map { it.toJsonElement() })
                is BencodeDictionary -> JsonObject(
                    element.map { (key, value) ->
                        key.stringOrHex() to value.toJsonElement()
                    }.toMap()
                )
                else -> throw SerializationException()
            }
        }
    )

/*object BencodeElementSerializer : KSerializer<BencodeElement> {
    override val descriptor = PrimitiveSerialDescriptor("BencodeElementSerializer", STRING)

    override fun deserialize(decoder: Decoder): BencodeElement {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: BencodeElement) {
        TODO("Not yet implemented")
    }
}

object BencodeByteStringSerializer : KSerializer<BencodeByteString> {
    override val descriptor = PrimitiveSerialDescriptor("BencodeByteStringSerializer", STRING)

    override fun deserialize(decoder: Decoder): BencodeByteString {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: BencodeByteString) {
        TODO("Not yet implemented")
    }
}

object BencodeIntegerSerializer : KSerializer<BencodeInteger> {
    override val descriptor = PrimitiveSerialDescriptor("BencodeIntegerSerializer", STRING)

    override fun deserialize(decoder: Decoder): BencodeInteger {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: BencodeInteger) {
        TODO("Not yet implemented")
    }
}

object BencodeListSerializer : KSerializer<BencodeList> {
    private object BencodeListDescriptor : SerialDescriptor by ListSerializer(BencodeElementSerializer).descriptor {
        override val serialName = "BencodeList"
    }

    override val descriptor: SerialDescriptor = BencodeListDescriptor

    override fun serialize(encoder: Encoder, value: BencodeList) {
        ListSerializer(BencodeElementSerializer).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder) =
        BencodeList(ListSerializer(BencodeElementSerializer).deserialize(decoder))
}

object BencodeDictionarySerializer : KSerializer<BencodeDictionary> {
    private object BencodeDictionaryDescriptor : SerialDescriptor by MapSerializer(BencodeByteString.serializer(), BencodeElementSerializer).descriptor {
        override val serialName = "BencodeDictionary"
    }

    override val descriptor: SerialDescriptor = BencodeDictionaryDescriptor

    override fun serialize(encoder: Encoder, value: BencodeDictionary) {
        MapSerializer(BencodeByteString.serializer(), BencodeElementSerializer).serialize(encoder,value)
    }

    override fun deserialize(decoder: Decoder) =
        BencodeDictionary(MapSerializer(BencodeByteString.serializer(), BencodeElementSerializer).deserialize(decoder))
}
*/
