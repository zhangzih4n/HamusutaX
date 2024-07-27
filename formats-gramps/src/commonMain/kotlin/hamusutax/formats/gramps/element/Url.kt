package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("url")
data class Url(
    val href: String, // http://library.gramps-project.org
    val type: String // Web Home
)
