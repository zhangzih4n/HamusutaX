package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("person")
data class Person(
    val handle: String, // _f3a1eff63bc6a08f0aeafa8d055
    val change: String, // 1696446481
    val id: String, // I0000
    val priv: Int? = null, // 1 TODO: 检查作用
    @XmlElement(true) val gender: Gender,
    @XmlElement(true) val nameElements: List<Name>,
    @XmlElement(true) val addressElements: List<Address>,
    @XmlElement(true) @SerialName("eventref") val eventRefElements: List<EventRef>,
    @XmlElement(true) @SerialName("objref") val objRefElements: List<ObjRef>,
    @XmlElement(true) val attributeElements: List<Attribute>,
    @XmlElement(true) @SerialName("childof") val childOfElements: List<ChildOf>,
    @XmlElement(true) @SerialName("parentin") val parentInElements: List<ParentIn>,
    @XmlElement(true) @SerialName("noteref") val noteRefElements: List<NoteRef>,
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>,
    @XmlElement(true) @SerialName("tagref") val tagRefElements: List<TagRef>,
    @XmlElement(true) @SerialName("lds_ord") val ldsOrdElements: List<LdsOrd>,
    @XmlElement(true) @SerialName("url") val url: List<Url>,
    @XmlElement(true) @SerialName("personref") val personRef: List<PersonRef>,
)
