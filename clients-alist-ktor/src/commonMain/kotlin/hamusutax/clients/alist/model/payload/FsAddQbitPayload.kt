package hamusutax.clients.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class FsAddQbitPayload(
    val urls: List<String>,
    val path: String
)
