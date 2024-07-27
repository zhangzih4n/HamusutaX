package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("region")
data class Region(
    @SerialName("corner1_x") val corner1X: String,
    @SerialName("corner1_y") val corner1Y: String,
    @SerialName("corner2_x") val corner2X: String,
    @SerialName("corner2_y") val corner2Y: String,
)
