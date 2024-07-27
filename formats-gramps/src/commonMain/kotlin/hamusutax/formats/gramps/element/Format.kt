package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("format")
data class Format(
    val number: Int,
    val name: String,
    @SerialName("fmt_str") val formatString: String,
    val active: Int
)
