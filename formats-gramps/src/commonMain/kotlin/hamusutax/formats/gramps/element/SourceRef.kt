package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("sourceref")
data class SourceRef(
    @SerialName("hlink") val hyperlink: String // _f3a1eff63c62b4ecf143638ac23
)
