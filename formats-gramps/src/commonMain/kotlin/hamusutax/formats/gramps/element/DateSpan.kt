package hamusutax.formats.gramps.element

import hamusutax.formats.gramps.attribute.DateQuality
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("datespan")
data class DateSpan(
    val start: String,
    val stop: String,
    @XmlElement(false) val quality: DateQuality? = null
)
