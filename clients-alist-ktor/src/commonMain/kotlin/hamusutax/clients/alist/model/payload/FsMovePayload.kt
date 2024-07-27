package hamusutax.clients.alist.model.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsMovePayload(
    @SerialName("src_dir") val srcDir: String,
    @SerialName("dst_dir") val dstDir: String,
    val names: List<String>
)
