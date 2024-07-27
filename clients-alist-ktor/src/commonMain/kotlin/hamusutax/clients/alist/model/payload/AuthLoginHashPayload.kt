package hamusutax.clients.alist.model.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginHashPayload(
    val username: String,
    val password: String,
    @SerialName("otp_code") val otpCode: String
)
