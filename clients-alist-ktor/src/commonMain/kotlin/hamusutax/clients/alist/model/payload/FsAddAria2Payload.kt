package hamusutax.clients.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class FsAddAria2Payload(
    val urls: List<String>,
    val path: String
)
