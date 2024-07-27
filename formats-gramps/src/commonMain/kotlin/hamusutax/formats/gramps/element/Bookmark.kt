package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("bookmark")
data class Bookmark(
    val target: String,
    @SerialName("hlink") val hyperlink: String
)
