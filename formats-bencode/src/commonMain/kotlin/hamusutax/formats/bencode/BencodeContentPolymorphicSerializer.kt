package hamusutax.formats.bencode

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializerOrNull
import kotlin.reflect.KClass

@OptIn(InternalSerializationApi::class)
abstract class BencodeContentPolymorphicSerializer<T : Any>(private val baseClass: KClass<T>) : KSerializer<T> {
    override val descriptor =
        buildSerialDescriptor("BencodeContentPolymorphicSerializer<${baseClass.simpleName}>", PolymorphicKind.SEALED)

    final override fun serialize(encoder: Encoder, value: T) {
        val actualSerializer =
            encoder.serializersModule.getPolymorphic(baseClass, value)
                ?: value::class.serializerOrNull()
                ?: throwSubtypeNotRegistered(value::class, baseClass)
        @Suppress("UNCHECKED_CAST")
        (actualSerializer as KSerializer<T>).serialize(encoder, value)
    }

    final override fun deserialize(decoder: Decoder): T {
        val input = decoder.asBencodeDecoder()
        val tree = input.decodeBencodeElement()
        val actualSerializer = selectDeserializer(tree) as KSerializer<T>
        return input.bencode.decodeFromBencodeElement(actualSerializer, tree)
    }

    protected abstract fun selectDeserializer(element: BencodeElement): DeserializationStrategy<T>

    private fun throwSubtypeNotRegistered(subClass: KClass<*>, baseClass: KClass<*>): Nothing {
        val subClassName = subClass.simpleName ?: "$subClass"
        val scope = "in the scope of '${baseClass.simpleName}'"
        throw SerializationException(
            "Class '${subClassName}' is not registered for polymorphic serialization $scope.\n" +
                    "Mark the base class as 'sealed' or register the serializer explicitly.")
    }
}
