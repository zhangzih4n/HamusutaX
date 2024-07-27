package hamusutax.clients.alist.model.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsDirsPayload(
    val path: String,
    val password: String,
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    val refresh: Boolean
)
