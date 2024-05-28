package hamusutax.ktor.clientapp.alist.model.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsAddQbitPayload(
    val urls: List<String>,
    val path: String
)
