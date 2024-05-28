package hamusutax.ktor.clientapp.alist.model.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminStorageInfo(
    @SerialName("mount_path") val mountPath: String,
    val order: Int,
    val remark: String,
    @SerialName("cache_expiration") val cacheExpiration: Int,
    @SerialName("web_proxy") val webProxy: Boolean,
    @SerialName("webdav_policy") val webdavPolicy: String,
    @SerialName("down_proxy_url") val downProxyUrl: String,
    @SerialName("extract_folder") val extractFolder: String,
    @SerialName("enable_sign") val enableSign: Boolean,
    val driver: String,
    @SerialName("order_by") val orderBy: String,
    @SerialName("order_direction") val orderDirection: String,
    val addition: String
)
