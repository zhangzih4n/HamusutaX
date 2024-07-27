package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("objref")
data class ObjRef(
    @SerialName("hlink") val hyperlink: String, // _f3a1eff63c62b4ecf143638ac23
    @XmlElement(true) val attributeElements: List<Attribute>,
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>,
    @XmlElement(true) val region: List<Region>
)
