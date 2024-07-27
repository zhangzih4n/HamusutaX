package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("tagref")
data class TagRef(
    @SerialName("hlink") val hyperlink: String // _c5a0f183dde9ccfe90d5f68ab7
)
