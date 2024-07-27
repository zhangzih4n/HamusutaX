package hamusutax.formats.gramps.element

import hamusutax.formats.gramps.attribute.DateQuality
import hamusutax.formats.gramps.attribute.DateValType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("dateval")
data class DateVal(
    val `val`: String, // 1970-01-01
    @XmlElement(false) val type: DateValType? = null,
    @SerialName("cformat") val calendarFormat: String? = null, // Julian
    @SerialName("dualdated") val dualDated: String? = null, // 1
    @SerialName("newyear") val newYear: String? = null, // Mar25
    @XmlElement(false) val quality: DateQuality? = null // estimated
)
