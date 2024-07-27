package hamusutax.clients.alist.model.response


import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginHashResponse(
    val token: String
)
