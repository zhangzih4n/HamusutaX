package hamusutax.ktor.clientapp.alist.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class FsGetResponse(
    /**
     * 文件名
     */
    val name: String,
    /**
     * 大小
     */
    val size: Int,
    /**
     * 是否是文件夹
     */
    @SerialName("is_dir") val isDir: Boolean,
    /**
     * 修改时间
     */
    val modified: String,
    /**
     * 签名
     */
    val sign: String,
    /**
     * 缩略图
     */
    val thumb: String,
    /**
     * 类型
     */
    val type: Int,
    /**
     * 原始 URL
     */
    @SerialName("raw_url") val rawUrl: String,
    /**
     * 说明
     */
    val readme: String,
    val provider: String,
    val related: JsonElement? // TODO: 未知类型，官方文档里也未提及
)
