package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("address")
data class Address(
    @XmlElement(true) @SerialName("dateval") val dateVal: DateVal? = null, // 2000-01-01
    @XmlElement(true) val street: String? = null, // XX街道XX小区
    @XmlElement(true) val locality: String? = null, // 市中区
    @XmlElement(true) val city: String? = null, // 济南市
    @XmlElement(true) val state: String? = null, // 山东省
    @XmlElement(true) val country: String? = null, // 中华人民共和国
    @XmlElement(true) val postal: String? = null, // 271000
    @XmlElement(true) val phone: String? = null, // +86(538)88881234
    @XmlElement(true) @SerialName("citationref") val citationRef: CitationRef? = null // 2000-01-01
)
