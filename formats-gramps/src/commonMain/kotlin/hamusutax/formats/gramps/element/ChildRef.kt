package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("childref")
data class ChildRef(
    @SerialName("hlink") val hyperlink: String, // _f3a1eff63c62b4ecf143638ac23
    @SerialName("frel") val fatherRel: String? = null,
    @SerialName("mrel") val motherRel: String? = null,
    @XmlElement(true) @SerialName("citationref") val citationRef: CitationRef? = null
)
