package hamusutax.formats.gramps.element

import hamusutax.formats.gramps.attribute.EventRole
import hamusutax.formats.gramps.serializer.EventRoleSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("eventref")
data class EventRef(
    @SerialName("hlink") val hyperlink: String, // _f3a1eff63c62b4ecf143638ac23
    @Serializable(with = EventRoleSerializer::class) val role: EventRole, // Family
    @XmlElement(true) val attributeElements: List<Attribute>,
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>
)
