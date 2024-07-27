package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("object")
data class Object(
    val handle: String, // _43NT6DHH0TBN0PKVC
    val change: String, // 1696446481
    val id: String, // O0002
    @XmlElement(true) val file: File,
    @XmlElement(true) @SerialName("dateval") val dateVal: DateVal? = null,
    @XmlElement(true) @SerialName("tagref") val tagRef: TagRef? = null,
    @XmlElement(true) @SerialName("attribute") val attribute: Attribute? = null,
    @XmlElement(true) @SerialName("citationref") val citationRef: CitationRef? = null,
)
