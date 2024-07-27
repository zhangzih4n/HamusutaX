package hamusutax.clients.alist.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AdminTaskDoneResponseItem(
    val id: String,
    val name: String,
    val state: String,
    val status: String,
    val progress: Int,
    val error: String
)
