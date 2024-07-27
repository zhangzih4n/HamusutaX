package hamusutax.clients.alist.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeResponse(
    /**
     * ID
     */
    val id: Int,
    /**
     * 用户名
     */
    val username: String,
    /**
     * 密码
     */
    val password: String,
    /**
     * 根目录
     */
    @SerialName("base_path") val basePath: String,
    /**
     * 角色
     */
    val role: Int,
    /**
     * 是否禁用
     */
    val disabled: Boolean,
    /**
     * 权限
     */
    val permission: Int,
    /**
     * SSO ID
     */
    @SerialName("sso_id") val ssoId: String,
    /**
     * 是否开启二步验证
     */
    val otp: Boolean
)
