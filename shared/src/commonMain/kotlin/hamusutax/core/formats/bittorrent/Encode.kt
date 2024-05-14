@file:Suppress("UNUSED")
package hamusutax.core.formats.bittorrent

import hamusutax.core.hash.sha256
import hamusutax.core.number.isPowerOf
import hamusutax.core.number.nextPowerOf

/**
 * 计算 [BitTorrent V2](http://www.bittorrent.org/beps/bep_0003.html) Piece 列表的 PiecesRoot
 *
 * @param pieceLength 分块大小，单位 Byte
 *
 * @return 计算后的 PiecesRoot
 */
internal fun List<ByteArray>.piecesRoot(pieceLength: Int): ByteArray {
    var list = if (size.isPowerOf(2)) this
    else this + List(size.nextPowerOf(2) - size) { Padding[pieceLength] }

    while (list.size > 1) {
        list = list.chunked(2).map { (it[0] + it[1]).sha256() }
    }
    return list.first()
}
