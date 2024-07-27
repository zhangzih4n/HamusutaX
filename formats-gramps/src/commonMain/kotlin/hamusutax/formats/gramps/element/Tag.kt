package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("tag")
data class Tag(
    val handle: String,
    val change: String,
    val name: String,
    val color: String,
    val priority: String
)
