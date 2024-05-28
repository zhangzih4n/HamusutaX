package hamusutax.ktor.clientapp.alist.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsSearchResponse(
    val content: List<Content>,
    val total: Int
) {
    @Serializable
    data class Content(
        val parent: String,
        val name: String,
        @SerialName("is_dir") val isDir: Boolean,
        val size: Int,
        val type: Int
    )
}
