package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("place")
data class Place(
    @SerialName("hlink") val hyperlink: String // _f3a1eff67713db25b1629ae363b
)
