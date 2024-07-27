package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("lds_ord")
data class LdsOrd(
    val type: String,
    @XmlElement(true) @SerialName("dateval") val dateVal: DateVal? = null,
    @XmlElement(true) @SerialName("temple") val temple: Temple? = null,
    @XmlElement(true) @SerialName("place") val place: Place? = null,
    @XmlElement(true) @SerialName("status") val status: Status? = null,
    @XmlElement(true) @SerialName("sealed_to") val sealedTo: SealedTo? = null,
    @XmlElement(true) @SerialName("citationref") val citationRef: CitationRef? = null,
    @XmlElement(true) @SerialName("attribute") val attribute: Attribute? = null,
)
