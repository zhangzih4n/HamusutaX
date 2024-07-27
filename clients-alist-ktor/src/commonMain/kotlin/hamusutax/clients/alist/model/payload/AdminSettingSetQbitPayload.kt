package hamusutax.clients.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class AdminSettingSetQbitPayload(
    val url: String,
    val seedtime: String
)
