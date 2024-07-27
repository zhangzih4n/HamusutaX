package hamusutax.formats.gramps.element

import hamusutax.formats.gramps.attribute.Type
import hamusutax.formats.gramps.serializer.TypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("event")
data class Event(
    val handle: String, // _f3a1eff640244d54e387938ad90
    val change: String, // 1696446482
    val id: String, // E0000
    @XmlElement(true) @Serializable(with = TypeSerializer::class) val type: Type, // Birth
    @XmlElement(true) @SerialName("dateval") val dateVal: DateVal? = null,
    @XmlElement(true) @SerialName("daterange") val dateRange: DateRange? = null,
    @XmlElement(true) @SerialName("datespan") val dateSpan: DateSpan? = null,
    @XmlElement(true) val place: Place? = null,
    @XmlElement(true) @SerialName("noteref") val noteRefElements: List<NoteRef>,
    @XmlElement(true) val attributeElements: List<Attribute>,
    @XmlElement(true) val description: String? = null, // 农历正月初一
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>,
    @XmlElement(true) @SerialName("objref") val objRefElements: List<ObjRef>,

    )
