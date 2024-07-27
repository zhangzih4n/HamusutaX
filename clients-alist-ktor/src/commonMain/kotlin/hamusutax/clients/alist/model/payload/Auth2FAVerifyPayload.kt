package hamusutax.clients.alist.model.payload

import kotlinx.serialization.Serializable

@Serializable
data class Auth2FAVerifyPayload(
    val code: String,
    val secret: String
)
