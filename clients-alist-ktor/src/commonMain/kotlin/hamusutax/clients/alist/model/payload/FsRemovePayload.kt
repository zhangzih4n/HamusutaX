package hamusutax.clients.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class FsRemovePayload(
    val names: List<String>,
    val dir: String
)
