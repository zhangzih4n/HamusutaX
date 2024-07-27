package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("researcher")
data class Researcher(
    @XmlElement(true) @SerialName("resname") val resName: String? = null,
    @XmlElement(true) @SerialName("resaddr") val resAddr: String? = null,
    @XmlElement(true) @SerialName("reslocality") val resLocality: String? = null,
    @XmlElement(true) @SerialName("rescity") val resCity: String? = null,
    @XmlElement(true) @SerialName("resstate") val resState: String? = null,
    @XmlElement(true) @SerialName("rescountry") val resCountry: String? = null,
    @XmlElement(true) @SerialName("respostal") val resPostal: String? = null,
    @XmlElement(true) @SerialName("resphone") val resPhone: String? = null,
    @XmlElement(true) @SerialName("resemail") val resEmail: String? = null
)
