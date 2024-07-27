package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("header")
data class Header(
    @XmlElement(true) val created: Created,
    @XmlElement(true) val researcher: Researcher,
    @XmlElement(true) @SerialName("mediapath") val mediaPath: String? = null
)
