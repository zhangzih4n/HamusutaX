package hamusutax.formats.gramps.element

import hamusutax.formats.gramps.attribute.AttributeType
import hamusutax.formats.gramps.serializer.AttributeTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("attribute")
data class Attribute(
    val priv: Int? = null,
    @Serializable(with = AttributeTypeSerializer::class) @XmlElement(false) val type: AttributeType,
    val value: String,
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>,
    @XmlElement(true) @SerialName("noteref") val noteRefElements: List<NoteRef>
)
