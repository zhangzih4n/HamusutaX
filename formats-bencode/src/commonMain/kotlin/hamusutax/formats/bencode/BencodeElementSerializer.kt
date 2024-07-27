package hamusutax.formats.bencode

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object BencodeElementSerializer : KSerializer<BencodeElement> {
    override val descriptor =
        PrimitiveSerialDescriptor("BencodeElementSerializer", STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.asBencodeDecoder().decodeBencodeElement()

    override fun serialize(encoder: Encoder, value: BencodeElement) =
        encoder.asBencodeEncoder().encodeBencodeElement(value)
}

object BencodeByteStringSerializer : KSerializer<BencodeByteString> {
    override val descriptor =
        PrimitiveSerialDescriptor("BencodeByteStringSerializer", STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.asBencodeDecoder().decodeBencodeElement() as BencodeByteString

    override fun serialize(encoder: Encoder, value: BencodeByteString) =
        encoder.asBencodeEncoder().encodeBencodeElement(value)
}

object BencodeIntegerSerializer : KSerializer<BencodeInteger> {
    override val descriptor =
        PrimitiveSerialDescriptor("BencodeIntegerSerializer", STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.asBencodeDecoder().decodeBencodeElement() as BencodeInteger

    override fun serialize(encoder: Encoder, value: BencodeInteger) =
        encoder.asBencodeEncoder().encodeBencodeElement(value)
}

object BencodeListSerializer : KSerializer<BencodeList> {
    private object BencodeListDescriptor : SerialDescriptor by ListSerializer(
        BencodeElementSerializer
    ).descriptor {
        override val serialName = "BencodeList"
    }

    override val descriptor: SerialDescriptor = BencodeListDescriptor

    override fun serialize(encoder: Encoder, value: BencodeList) {
        val composite = encoder.beginStructure(descriptor)
        value.forEachIndexed { index, bencodeElement ->
            composite.encodeSerializableElement(descriptor, index, BencodeElementSerializer, bencodeElement)
        }
        composite.endStructure(descriptor)
        ListSerializer(BencodeElementSerializer).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder) =
        decoder.asBencodeDecoder().decodeBencodeElement() as BencodeList
}

object BencodeDictionarySerializer : KSerializer<BencodeDictionary> {
    private object BencodeDictionaryDescriptor : SerialDescriptor by MapSerializer(
        BencodeByteString.serializer(), BencodeElementSerializer
    ).descriptor {
        override val serialName = "BencodeDictionary"
    }

    override val descriptor: SerialDescriptor = BencodeDictionaryDescriptor

    override fun serialize(encoder: Encoder, value: BencodeDictionary) =
        MapSerializer(BencodeByteString.serializer(), BencodeElementSerializer).serialize(encoder, value)

    override fun deserialize(decoder: Decoder) =
        BencodeDictionary(MapSerializer(BencodeByteString.serializer(), BencodeElementSerializer).deserialize(decoder))
}

internal fun Decoder.asBencodeDecoder() = this as? BencodeDecoder
    ?: throw IllegalStateException(
        "This serializer can be used only with Bencode format." +
                "Expected Decoder to be BencodeDecoder, got ${this::class}"
    )

internal fun Encoder.asBencodeEncoder() = this as? BencodeEncoder
    ?: throw IllegalStateException(
        "This serializer can be used only with Bencode format." +
                "Expected Encoder to be BencodeEncoder, got ${this::class}"
    )
