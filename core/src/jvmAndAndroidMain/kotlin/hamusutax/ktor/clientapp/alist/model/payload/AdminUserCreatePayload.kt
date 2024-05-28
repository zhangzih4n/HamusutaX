package hamusutax.ktor.clientapp.alist.model.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminUserCreatePayload(
    val id: Int,
    val username: String,
    val password: String,
    @SerialName("base_path") val basePath: String,
    val role: Int,
    val permission: Int,
    val disabled: Boolean,
    @SerialName("sso_id") val ssoId: String
)
