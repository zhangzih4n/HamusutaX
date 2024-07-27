package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("repository")
data class Repository(
    val handle: String, // _43NT6DHH0TBN0PKVC
    val change: String, // 1696446481
    val id: String, // O0002
    @XmlElement(true) @SerialName("rname") val repositoryName: String,
    @XmlElement(true) val type: String,
    @XmlElement(true) val address: Address,
    @XmlElement(true) val url: Url? = null,
    @XmlElement(true) @SerialName("noteRef") val noteRef: NoteRef? = null
)
