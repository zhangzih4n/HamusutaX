package hamusutax.ktor.clientapp.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class AdminSettingSetAria2Payload(
    val uri: String,
    val secret: String
)
