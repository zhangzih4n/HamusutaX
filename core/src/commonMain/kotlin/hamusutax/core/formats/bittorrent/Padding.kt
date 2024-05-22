@file:Suppress("UNUSED")
package hamusutax.core.formats.bittorrent

import hamusutax.core.math.isPowerOf

internal class Padding {
    companion object {
        operator fun get(pieceLength: Int): ByteArray {
            paddingMap[pieceLength]?.let { return it }
            require(pieceLength.isPowerOf(2))
            val blocksPerPiece = pieceLength / BitTorrent.BLOCK_SIZE
            val padding = List(blocksPerPiece) { ByteArray(32) }
                .piecesRoot(blocksPerPiece)
            paddingMap[pieceLength] = padding
            return padding
        }

        private val paddingMap = mutableMapOf<Int, ByteArray>()

        val PADDING_16K = get(16 * 1024)
        val PADDING_32K = get(32 * 1024)
        val PADDING_64K = get(64 * 1024)
        val PADDING_128K = get(128 * 1024)
        val PADDING_256K = get(256 * 1024)
        val PADDING_512K = get(512 * 1024)
        val PADDING_1M = get(1024 * 1024)
        val PADDING_2M = get(2 * 1024 * 1024)
        val PADDING_4M = get(4 * 1024 * 1024)
        val PADDING_8M = get(8 * 1024 * 1024)
        val PADDING_16M = get(16 * 1024 * 1024)
        val PADDING_32M = get(32 * 1024 * 1024)
    }
}
