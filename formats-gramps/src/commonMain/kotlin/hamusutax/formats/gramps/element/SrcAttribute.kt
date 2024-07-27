package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("srcattribute")
data class SrcAttribute(
    val type: String,
    val value: String
)
