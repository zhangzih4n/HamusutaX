@file:Suppress("unused")
package hamusutax.formats.bittorrent.torrent

import hamusutax.formats.bencode.BencodeByteString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Torrent(
    /**
     * 主 Tracker URL
     */
    val announce: String? = null,
    /**
     * 备用 Tracker URL
     */
    @SerialName("announce-list") val announceList: List<List<String>>? = null,
    /**
     * 注释
     */
    val comment: String? = null,
    /**
     * 创建者
     */
    @SerialName("created by") val createdBy: String? = null,
    /**
     * 创建时间，格式为 Unix 时间戳
     */
    @SerialName("creation date") val creationDate: Long? = null,
    val info: Info,
    /**
     * BitTorrent V2 分块信息
     */
    @SerialName("piece layers") val pieceLayers: Map<String, String>? = null,
    @SerialName("url-list") val urlList: String? = null
) {
    @Serializable
    data class Info(
        /**
         * 文件属性，如填充、隐藏、符号链接等，仅单文件格式包含
         *
         * 标准文档：[BEP 47 - Padding files and extended file attributes](https://www.bittorrent.org/beps/bep_0047.html)
         */
        val attr: String? = null,
        /**
         * BitTorrent V1 文件信息（列表），仅多文件格式包含
         */
        val files: List<File>? = null,
        /**
         * BitTorrent V2 文件信息（树）
         */
        @SerialName("file tree") val filesTree: FileTree? = null,
        /**
         * 文件大小，仅单文件格式包含（单位：字节）
         */
        val length: Long? = null,
        /**
         * BitTorrent 版本
         */
        @SerialName("meta version") val metaVersion: Long? = null,
        /**
         * 单文件格式为文件名，多文件格式为根目录名
         */
        val name: String,
        /**
         * 分块大小，需要为 2 的整数次幂倍，最小值 16384（单位：字节）
         */
        @SerialName("piece length") val pieceLength: Long,
        /**
         * BitTorrent V1 分块信息
         */
        val pieces: ByteArray? = null,
        val publisher: String? = null,
        @SerialName("publisher-url") val publisherUrl: String? = null,
        /**
         * 根据文件本身的内容计算，不包括填充，可用于辅助文件重复数据删除，仅单文件格式包含
         *
         * 仅应被视为提示， 分块哈希值是完整性检查的规范参考
         */
        val sha1: String? = null,
        val source: String? = null
    ) {
        /**
         * BitTorrent V1 文件信息（列表）
         */
        @Serializable
        data class File(
            /**
             * 文件属性，如填充、隐藏、符号链接等
             *
             * 标准文档：[BEP 47 - Padding files and extended file attributes](https://www.bittorrent.org/beps/bep_0047.html)
             */
            val attr: String? = null,
            /**
             * ED2K 电驴链接
             */
            val ed2k: String? = null,
            /**
             * 文件大小，仅单文件格式包含（单位：字节）
             *
             * 符号链接的大小始终为零
             */
            val length: Long,
            /**
             * 相对于种子根目录的路径
             */
            val path: List<String>,
            /**
             * 根据文件本身的内容计算，不包含填充，可用于辅助文件重复数据删除
             *
             * 仅应被视为提示，分块哈希值是完整性检查的规范参考
             */
            @SerialName("path.utf8") val pathUtf8: String? = null,
            /**
             * 根据文件本身的内容计算，没有任何额外的填充，可用于辅助文件重复数据删除
             *
             * 仅应被视为提示，分块哈希值是完整性检查的规范参考
             */
            val sha1: String? = null,
            /**
             * 符号链接目标相对于种子根目录的路径
             */
            @SerialName("symlink path") val symlinkPath: List<String>? = null
        )

        @Serializable(with = FileTreeSerializer::class)
        sealed interface FileTree {
            val directory get() = this as? Directory
            val file get() = this as? File

            @JvmInline
            @Serializable
            value class Directory(
                private val content: Map<BencodeByteString, FileTree>,
            ) : FileTree, Map<BencodeByteString, FileTree> by content {
                override fun toString() =
                    content.entries.joinToString(", ", "Folder(", ")") { "${it.key}=${it.value}" }
            }

            @Serializable
            data class File(
                @SerialName("") val metadata: Metadata
            ) : FileTree {
                @Serializable
                data class Metadata(
                    val length: Long,
                    @SerialName("pieces root") val piecesRoot: ByteArray,
                ) {
                    override fun equals(other: Any?): Boolean {
                        if (this === other) return true
                        if (other == null || this::class != other::class) return false

                        other as Metadata

                        if (length != other.length) return false
                        if (!piecesRoot.contentEquals(other.piecesRoot)) return false

                        return true
                    }

                    override fun hashCode(): Int {
                        var result = length.hashCode()
                        result = 31 * result + piecesRoot.contentHashCode()
                        return result
                    }
                }

                override fun toString() =
                    "File(length=${metadata.length}, piecesRoot=<hex>${metadata.piecesRoot.toHexString(HexFormat.UpperCase)}</hex>)"
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false
            other as Info
            if (attr != other.attr) return false
            if (files != other.files) return false
            if (filesTree != other.filesTree) return false
            if (length != other.length) return false
            if (metaVersion != other.metaVersion) return false
            if (name != other.name) return false
            if (pieceLength != other.pieceLength) return false
            if (pieces != null) {
                if (other.pieces == null) return false
                if (!pieces.contentEquals(other.pieces)) return false
            } else if (other.pieces != null) return false
            if (publisher != other.publisher) return false
            if (publisherUrl != other.publisherUrl) return false
            if (sha1 != other.sha1) return false
            if (source != other.source) return false
            return true
        }

        override fun hashCode(): Int {
            var result = attr?.hashCode() ?: 0
            result = 31 * result + (files?.hashCode() ?: 0)
            result = 31 * result + (filesTree?.hashCode() ?: 0)
            result = 31 * result + (length?.hashCode() ?: 0)
            result = 31 * result + (metaVersion?.hashCode() ?: 0)
            result = 31 * result + name.hashCode()
            result = 31 * result + pieceLength.hashCode()
            result = 31 * result + (pieces?.contentHashCode() ?: 0)
            result = 31 * result + (publisher?.hashCode() ?: 0)
            result = 31 * result + (publisherUrl?.hashCode() ?: 0)
            result = 31 * result + (sha1?.hashCode() ?: 0)
            result = 31 * result + (source?.hashCode() ?: 0)
            return result
        }
    }
}
