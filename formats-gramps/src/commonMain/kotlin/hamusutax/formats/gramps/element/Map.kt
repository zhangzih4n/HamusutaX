package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("map")
data class Map(
    val type: String,
    val key: String,
    val value: String
)
