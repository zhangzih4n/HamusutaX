@file:Suppress("unused")
package hamusutax.formats.bencode

import kotlinx.io.Sink
import kotlinx.io.writeString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.StructureKind.CLASS
import kotlinx.serialization.descriptors.StructureKind.LIST
import kotlinx.serialization.descriptors.StructureKind.MAP
import kotlinx.serialization.descriptors.StructureKind.OBJECT
import kotlinx.serialization.encoding.CompositeEncoder

internal class StreamingBencodeEncoder(
    override val bencode: Bencode,
    override val sink: Sink
) : SinkBencodeEncoder(bencode, sink) {
    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        when (descriptor.kind as StructureKind) {
            MAP, CLASS, OBJECT -> sink.writeString(DICTIONARY_START)
            LIST -> sink.writeString(LIST_START)
        }
        return this
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        sink.writeString(END)
    }

    override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
        if (descriptor.kind == CLASS || descriptor.kind == OBJECT)
            encodeString(descriptor.getElementName(index))
        return true
    }
}
