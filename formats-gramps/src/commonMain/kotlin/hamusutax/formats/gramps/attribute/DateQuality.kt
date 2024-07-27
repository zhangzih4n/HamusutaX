package hamusutax.formats.gramps.attribute

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DateQuality {
    @SerialName("estimated") ESTIMATED
}
