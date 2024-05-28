package hamusutax.ktor.clientapp.alist.model.payload


import kotlinx.serialization.Serializable

@Serializable
data class FsRenamePayload(
    val name: String,
    val path: String
)
