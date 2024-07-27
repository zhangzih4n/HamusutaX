package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("coord")
data class Coord(
    /**
     * 经度
     */
    val long: String, // E100.000000
    /**
     * 纬度
     */
    val lat: String // N35.000000
)
