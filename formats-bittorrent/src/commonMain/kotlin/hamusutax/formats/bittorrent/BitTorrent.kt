@file:Suppress("unused")
package hamusutax.formats.bittorrent

import hamusutax.io.rawsource.readAtMostTo
import hamusutax.io.source.request
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.readByteArray

class BitTorrent {
    companion object {
        const val BLOCK_SIZE = 16 * 1024
        private const val BUFFER_SIZE = 8 * 1024 * 1024

        /**
         * 当只有一个 Piece 时， Piece 列表将清空，仅保留 PiecesRoot
         *
         * @param pieceLength 分块大小，单位为 Byte
         * @param padding 计算 BitTorrent V1 摘要时是否填充尾部
         *
         * @return PiecesRoot 与 Pieces 的对。
         * 当文件为空时，PiecesRoot 为空字节列表；
         * 当只有一个 Piece 时， Piece 字节列表将清空，仅保留 PiecesRoot
         */
        fun Source.encodeToBitTorrentCompatibility(
            pieceLength: Int = 256 * 1024,
            padding: Boolean = false
        ): Pair<ByteArray, Pair<ByteArray, ByteArray>> {
            val v1 = BitTorrentV1Digest(pieceLength)
            val v2 = BitTorrentV2Digest(pieceLength)

            val buffer = Buffer()
            while (true) {
                request(BUFFER_SIZE)
                if (readAtMostTo(buffer, BUFFER_SIZE) == -1L)
                    break
                val bytes = buffer.readByteArray()
                v1.update(bytes)
                v2.update(bytes)
            }
            return v1.digest(padding) to v2.digest()
        }

        fun Source.encodeToBitTorrentV1(
            pieceLength: Int = 256 * 1024,
            padding: Boolean = false
        ): ByteArray {
            val v1 = BitTorrentV1Digest(pieceLength)

            val buffer = Buffer()
            while (true) {
                request(BUFFER_SIZE)
                if (readAtMostTo(buffer, BUFFER_SIZE) == -1L)
                    break
                val bytes = buffer.readByteArray()
                v1.update(bytes)
            }
            return v1.digest(padding)
        }

        fun Source.encodeToBitTorrentV2(
            pieceLength: Int = 256 * 1024
        ): Pair<ByteArray, ByteArray> {
            val v2 = BitTorrentV2Digest(pieceLength)

            val bufferSize = 4 * 1024 * 1024
            val buffer = Buffer()
            while (true) {
                request(bufferSize)
                if (readAtMostTo(buffer, bufferSize) == -1L)
                    break
                val bytes = buffer.readByteArray()
                v2.update(bytes)
            }
            return v2.digest()
        }
    }
}
