package hamusutax.ktor.clientapp.alist.model.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * POST /api/auth/login
 *
 * 获取某个用户的临时 JWT Token
 */
@Serializable
data class AuthLoginPayload(
    /**
     * 用户名
     */
    val username: String,
    /**
     * 密码
     */
    val password: String,
    /**
     * 二步验证码
     */
    @SerialName("otp_code") val otpCode: String
)
