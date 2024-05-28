package hamusutax.ktor.clientapp.alist.model.response

import kotlinx.serialization.Serializable

/**
 * POST /api/auth/login
 */
@Serializable
data class AuthLoginResponse(
    val token: String
)
