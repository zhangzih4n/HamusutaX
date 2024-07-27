package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("people")
data class People(
    val home: String? = null, // _f3a1eff779427b4f332f91cfc1a
    @XmlElement(true) val person: List<Person>
)
