package hamusutax.ktor.clientapp.alist.model.payload

import kotlinx.serialization.Serializable

@Serializable
data class Auth2FAVerifyPayload(
    val code: String,
    val secret: String
)
