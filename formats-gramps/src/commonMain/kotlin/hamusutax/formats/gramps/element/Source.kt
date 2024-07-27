package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("source")
data class Source(
    val handle: String, // _f3a1eff63bc16aca4a84b0b0094
    val change: String, // 1696446481
    val id: String, // S0000
    @XmlElement(true) @SerialName("stitle") val sourceTitle: String, // World of the Wierd
    @XmlElement(true) @SerialName("sauthor") val sourceAuthor: String? = null, // John Jacob Jinglehiemerschmitt
    @XmlElement(true) @SerialName("spubinfo") val sourcePubInfo: String? = null, // Microfilm Public Library Great Falls
    @XmlElement(true) @SerialName("sabbrev") val sourceAbbrev: String? = null, // WOTW
    @XmlElement(true) @SerialName("noteref") val noteRefElements: List<NoteRef>,
    @XmlElement(true) @SerialName("objref") val objRefElements: List<ObjRef>,
    @XmlElement(true) @SerialName("srcattribute") val srcAttribute: SrcAttribute? = null,
    @XmlElement(true) @SerialName("reporef") val repoRefElements: List<RepoRef>
)
