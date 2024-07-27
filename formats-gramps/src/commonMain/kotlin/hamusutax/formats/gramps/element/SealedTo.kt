package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("sealed_to")
data class SealedTo(
    @SerialName("hlink") val hyperlink: String, // _8OUJQCUVZ0XML7BQLF
)
