package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("personref")
data class PersonRef(
    @SerialName("hlink") val hyperlink: String, // _VJFKQCFO7WESWPNKHE
    @SerialName("rel") val relationship: String, // Godfather
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>
)
