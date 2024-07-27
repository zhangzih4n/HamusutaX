package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("style")
data class Style(
    val name: String,
    val value: String? = null,
    val range: Range
)
