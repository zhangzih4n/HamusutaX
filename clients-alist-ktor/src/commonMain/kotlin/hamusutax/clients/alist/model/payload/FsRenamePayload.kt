package hamusutax.clients.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class FsRenamePayload(
    val name: String,
    val path: String
)
