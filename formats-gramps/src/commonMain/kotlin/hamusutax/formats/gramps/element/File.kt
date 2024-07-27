package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("file")
data class File(
    val src: String, // O2.jpg
    val mime: String, // image/jpeg
    val checksum: String? = null, // 352c7ae13b8b642471ecae6fa78ce206
    val description: String // Emil &amp; Gustaf Smit
)
