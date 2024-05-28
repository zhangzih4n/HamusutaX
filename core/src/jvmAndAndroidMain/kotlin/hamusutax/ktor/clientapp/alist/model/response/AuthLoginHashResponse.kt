package hamusutax.ktor.clientapp.alist.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginHashResponse(
    val token: String
)
