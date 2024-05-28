package hamusutax.ktor.clientapp.alist.model.payload

import kotlinx.serialization.Serializable

@Serializable
data class AdminSettingSavePayloadItem(
    val key: String,
    val value: String,
    val help: String,
    val type: String,
    val options: String,
    val group: Int,
    val flag: Int
)
