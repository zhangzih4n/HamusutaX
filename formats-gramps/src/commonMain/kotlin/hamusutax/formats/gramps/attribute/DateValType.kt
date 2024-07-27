package hamusutax.formats.gramps.attribute

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DateValType {
    @SerialName("about") ABOUT,
    @SerialName("before") BEFORE,
    @SerialName("after") AFTER
}
