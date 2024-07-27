package hamusutax.clients.alist.model.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsSearchPayload(
    val parent: String,
    val keywords: String,
    val scope: Int,
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    val password: String
)
