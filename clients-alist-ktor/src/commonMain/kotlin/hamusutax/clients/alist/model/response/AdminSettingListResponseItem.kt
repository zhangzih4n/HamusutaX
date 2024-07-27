package hamusutax.clients.alist.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AdminSettingListResponseItem(
    val key: String,
    val value: String,
    val help: String,
    val type: String,
    val options: String,
    val group: Int,
    val flag: Int
)
