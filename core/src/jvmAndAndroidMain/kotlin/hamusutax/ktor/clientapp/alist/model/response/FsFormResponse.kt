package hamusutax.ktor.clientapp.alist.model.response

import kotlinx.serialization.Serializable

@Serializable
data class FsFormResponse(
    val task: Task
) {
    @Serializable
    data class Task(
        val id: String,
        val name: String,
        val state: Int,
        val status: String,
        val progress: Int,
        val error: String
    )
}
