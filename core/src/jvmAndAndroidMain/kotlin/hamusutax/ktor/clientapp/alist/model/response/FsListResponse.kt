package hamusutax.ktor.clientapp.alist.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsListResponse(
    val content: List<Content>,
    /**
     * 总数
     */
    val total: Int,
    /**
     * 说明
     */
    val readme: String,
    /**
     * 是否可写入
     */
    val write: Boolean,
    val provider: String
) {
    @Serializable
    data class Content(
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
        val type: Int
    )
}
