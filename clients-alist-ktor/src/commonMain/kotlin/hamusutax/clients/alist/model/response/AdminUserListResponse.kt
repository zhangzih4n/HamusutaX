package hamusutax.clients.alist.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminUserListResponse(
    val content: List<Content>,
    val total: Int
) {
    @Serializable
    data class Content(
        val id: Int,
        val username: String,
        @SerialName("Salt") val salt: String,
        val password: String,
        @SerialName("base_path") val basePath: String,
        val role: Int,
        val disabled: Boolean,
        val permission: Int,
        @SerialName("sso_id") val ssoId: String
    )
}
