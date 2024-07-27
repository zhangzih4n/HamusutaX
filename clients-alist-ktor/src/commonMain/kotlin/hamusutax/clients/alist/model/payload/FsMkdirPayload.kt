package hamusutax.clients.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class FsMkdirPayload(
    val path: String
)
