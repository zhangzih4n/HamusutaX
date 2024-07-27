package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("pname")
data class PlaceName(
    val value: String, // XX市第一人民医院
    @SerialName("lang") val language: String? = null // en
)
