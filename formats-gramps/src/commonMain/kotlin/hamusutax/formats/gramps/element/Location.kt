package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("location")
data class Location(
    val street: String? = null, // XX街XX号
    val locality: String? = null, // XX区
    val city: String? = null, // XX市
    val state: String? = null, // XX省
    val country: String? = null, // 中国
    val postal: String? = null, // 100000
)
