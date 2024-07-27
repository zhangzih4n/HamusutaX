package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("bookmarks")
data class Bookmarks(
    @XmlElement(true) val bookmarkElements: List<Bookmark>
)
