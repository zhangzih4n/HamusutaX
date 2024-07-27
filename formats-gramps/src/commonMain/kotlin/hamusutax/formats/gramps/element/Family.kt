package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("family")
data class Family(
    val handle: String, // _f3a1eff63bc16aca4a84b0b0094
    val change: String, // 1696446481
    val id: String, // F0000
    @XmlElement(true) @SerialName("rel") val relationship: Rel,
    @XmlElement(true) val fatherElements: List<Father>,
    @XmlElement(true) val motherElements: List<Mother>,
    @XmlElement(true) @SerialName("eventref") val eventRefElements: List<EventRef>,
    @XmlElement(true) @SerialName("objref") val objRefElements: List<ObjRef>,
    @XmlElement(true) @SerialName("childref") val childRefElements: List<ChildRef>,
    @XmlElement(true) @SerialName("attribute") val attributeElements: List<Attribute>,
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>,
    @XmlElement(true) @SerialName("noteref") val noteRefElements: List<NoteRef>,
    @XmlElement(true) @SerialName("tagref") val tagRefElements: List<TagRef>,
    @XmlElement(true) @SerialName("lds_ord") val ldsOrd: List<LdsOrd>
)
