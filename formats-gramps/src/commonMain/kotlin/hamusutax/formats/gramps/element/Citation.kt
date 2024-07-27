package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("citation")
data class Citation(
    val handle: String, // _f3a1eff63bc16aca4a84b0b0094
    val change: String, // 1696446481
    val id: String, // C0000
    @XmlElement(true) @SerialName("dateval") val dateVal: DateVal? = null,
    @XmlElement(true) val page: String? = null, // Page 11 2/3.
    @XmlElement(true) val confidence: Int, // 4
    @XmlElement(true) @SerialName("noteref") val noteRefElements: List<NoteRef>,
    @XmlElement(true) @SerialName("sourceref") val sourceRefElements: List<SourceRef>
)
