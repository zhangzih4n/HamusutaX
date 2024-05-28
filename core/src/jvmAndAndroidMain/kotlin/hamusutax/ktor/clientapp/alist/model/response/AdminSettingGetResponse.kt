package hamusutax.ktor.clientapp.alist.model.response


import kotlinx.serialization.Serializable

@Serializable
data class AdminSettingGetResponse(
    val key: String,
    val value: String,
    val help: String,
    val type: String,
    val options: String,
    val group: Int,
    val flag: Int
)
