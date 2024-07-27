package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("gender")
enum class Gender {
    @SerialName("U") UNKNOWN,
    @SerialName("M") MALE,
    @SerialName("F") FEMALE
}
