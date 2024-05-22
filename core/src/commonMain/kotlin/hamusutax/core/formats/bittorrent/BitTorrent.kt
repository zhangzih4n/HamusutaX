@file:Suppress("UNUSED")
package hamusutax.core.formats.bittorrent

import hamusutax.core.collections.bytearray.toByteArray
import hamusutax.core.hash.sha1
import hamusutax.core.hash.sha256
import hamusutax.core.io.rawsource.readAtMostTo
import hamusutax.core.io.buffer.isEmpty
import hamusutax.core.io.source.request
import hamusutax.core.math.nextPowerOf
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.readByteArray

class BitTorrent {
    /**
     * 当只有一个 Piece 时， Piece 列表将清空，仅保留 PiecesRoot
     *
     * @param pieceLength 分块大小，单位为 Byte
     * @param isPadding 计算 BitTorrent V1 时是否填充尾部
     *
     * @return PiecesRoot 与 Pieces 的对。
     * 当文件为空时，PiecesRoot 为空字节列表；
     * 当只有一个 Piece 时， Piece 字节列表将清空，仅保留 PiecesRoot
     */
    fun Source.encodeToBitTorrentCompatibility(
        pieceLength: Int = 256 * 1024,
        isPadding: Boolean = false
    ): Triple<ByteArray, ByteArray, ByteArray> {
        val blocksPerPiece = pieceLength / BLOCK_SIZE
        val piecesV1 = Buffer()
        val piecesV2 = mutableListOf<ByteArray>()

        val blocksV1 = Buffer()
        val blocksV2 = mutableListOf<ByteArray>()
        val buffer = Buffer()
        while (true) {
            for (i in 0..<blocksPerPiece) {
                request(BLOCK_SIZE)
                readAtMostTo(buffer, BLOCK_SIZE)
                if (buffer.isEmpty())
                    break

                buffer.readByteArray().let { block ->
                    blocksV1.write(block)
                    blocksV2.add(block.sha256())
                }
            }

            if (blocksV2.isEmpty())
                break

            // 填充
            if (blocksV2.size != blocksPerPiece) {
                if (isPadding) {
                    blocksV1.write(ByteArray(pieceLength - blocksV1.size.toInt()))
                }

                // 若文件不足一个 Piece，则填充大小至下一个 2 的整数次幂；
                // 若文件超过一个 Piece，则将最后一个 Piece 填充至完整
                val leavesRequired =
                    if (piecesV2.isEmpty()) blocksV2.size.nextPowerOf(2) else pieceLength
                val paddings = List(leavesRequired - blocksV2.size) { ByteArray(32) }
                blocksV2.addAll(paddings)
            }

            piecesV1.write(blocksV1.readByteArray().sha1())
            piecesV2.add(blocksV2.piecesRoot(pieceLength))
            blocksV2.clear()
        }

        // 计算 PiecesRoot
        val piecesRoot = when (piecesV2.size) {
            0 -> ByteArray(0)
            1 -> piecesV2.first().also { piecesV2.clear() }
            else -> piecesV2.piecesRoot(pieceLength)
        }

        return Triple(piecesV1.readByteArray(), piecesRoot, piecesV2.toByteArray())
    }

    companion object {
        const val BLOCK_SIZE = 16 * 1024
    }
}
