package hamusutax.ktor.clientapp.alist.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AdminTaskUndoneResponseItem(
    val id: String,
    val name: String,
    val state: String,
    val status: String,
    val progress: Int,
    val error: String
)
