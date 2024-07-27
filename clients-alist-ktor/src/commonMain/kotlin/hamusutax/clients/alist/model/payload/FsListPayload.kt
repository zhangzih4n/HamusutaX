package hamusutax.clients.alist.model.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsListPayload(
    /**
     * 路径
     */
    val path: String,
    /**
     * 密码
     */
    val password: String,
    /**
     * 页数
     */
    val page: Int,
    /**
     * 每页数目
     */
    @SerialName("per_page") val perPage: Int,
    /**
     * 是否强制刷新
     */
    val refresh: Boolean
)
