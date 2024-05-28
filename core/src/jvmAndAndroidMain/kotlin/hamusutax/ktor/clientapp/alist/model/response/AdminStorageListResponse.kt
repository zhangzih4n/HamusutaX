package hamusutax.ktor.clientapp.alist.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminStorageListResponse(
    val content: List<Content>,
    val total: Int
) {
    @Serializable
    data class Content(
        val id: Int,
        @SerialName("mount_path") val mountPath: String,
        val order: Int,
        val driver: String,
        @SerialName("cache_expiration") val cacheExpiration: Int,
        val status: String,
        val addition: String,
        val remark: String,
        val modified: String,
        val disabled: Boolean,
        @SerialName("enable_sign") val enableSign: Boolean,
        @SerialName("order_by") val orderBy: String,
        @SerialName("order_direction") val orderDirection: String,
        @SerialName("extract_folder") val extractFolder: String,
        @SerialName("web_proxy") val webProxy: Boolean,
        @SerialName("webdav_policy") val webdavPolicy: String,
        @SerialName("down_proxy_url") val downProxyUrl: String
    )
}
