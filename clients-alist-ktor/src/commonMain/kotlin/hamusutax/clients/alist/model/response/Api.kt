package hamusutax.clients.alist.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Api<T>(
    val code: Int,
    val message: String,
    val data: T?
)
