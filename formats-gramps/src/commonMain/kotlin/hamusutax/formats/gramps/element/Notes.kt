package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("notes")
data class Notes(
    @XmlElement(true) val noteElements: List<Note>
)
