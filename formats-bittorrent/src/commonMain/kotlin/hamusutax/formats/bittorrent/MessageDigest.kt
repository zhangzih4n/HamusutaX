package hamusutax.formats.bittorrent

import hamusutax.collections.bytearray.emptyByteArray
import hamusutax.io.buffer.isEmpty
import hamusutax.io.buffer.isNotEmpty
import hamusutax.io.source.readAtMost
import hamusutax.io.source.request
import hamusutax.math.isPowerOf
import hamusutax.math.nextPowerOf
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.readByteArray
import org.kotlincrypto.hash.sha1.SHA1
import org.kotlincrypto.hash.sha2.SHA256

/**
 * @param pieceLength 每个 Piece 的大小，单位为字节。需要为 2 的整数次幂，范围为 16K~32M
 */
class BitTorrentV1Digest(val pieceLength: Int = 256 * 1024) {
    init {
        require(pieceLength.isPowerOf(2))
        require(pieceLength in 16 * 1024..32 * 1024 * 1024)
    }

    private val digester = SHA1()
    private val pieces = Buffer()
    private val temp = Buffer()

    fun update(source: ByteArray) {
        temp.write(source)
        while (temp.request(pieceLength)) {
            pieces.write(temp.readByteArray(pieceLength).sha1())
        }
    }

    fun digest(padding: Boolean = false): ByteArray {
        if (temp.isNotEmpty()) {
            if (padding) {
                temp.write(ByteArray(pieceLength - temp.size.toInt()))
            }
            pieces.write(temp.readByteArray().sha1())
        }
        return pieces.readByteArray()
    }

    private inline fun ByteArray.sha1(): ByteArray {
        digester.update(this)
        return digester.digest()
    }
}

private const val BLOCK_SIZE = 16 * 1024

class BitTorrentV2Digest(pieceLength: Int = 256 * 1024) {
    init {
        require(pieceLength.isPowerOf(2))
        require(pieceLength in 16 * 1024..32 * 1024 * 1024)
    }

    private val digester = SHA256()
    private val pieces = Buffer()
    private val blocks = Buffer()
    private val block = Buffer() // 不足一个块时将数据暂存于此
    private val piecesRootTemp1 = Buffer()
    private val piecesRootTemp2 = Buffer()
    private val blocksPerPiece = pieceLength / BitTorrent.BLOCK_SIZE
    private val paddingBytes = ByteArray(blocksPerPiece * 32).piecesRoot()

    fun update(source: ByteArray) {
        block.write(source)
        while (block.request(BLOCK_SIZE)) {
            blocks.write(block.readByteArray(BLOCK_SIZE).sha256())
        }
        while (blocks.request(blocksPerPiece * 32)) {
            pieces.write(blocks.readByteArray(blocksPerPiece * 32).piecesRoot())
        }
    }

    fun update(source: Source, bufferSize: Int = 16384) {
        while (!source.exhausted()) {
            source.request(bufferSize)
            update(source.readAtMost(bufferSize.toLong()))
        }
    }

    fun digest(): Pair<ByteArray, ByteArray> {
        if (pieces.isEmpty() && blocks.isEmpty() && block.isEmpty()) {
            return emptyByteArray() to emptyByteArray()
        }
        if (block.isNotEmpty()) {
            blocks.write(block.readByteArray().sha256())
        }
        if (blocks.isNotEmpty()) {
            // 若不足一个 Piece 则填充大小至下一个 2 的整数次幂。否则填充至完整 Piece 大小
            val leavesRequired =
                if (pieces.isEmpty()) (blocks.size / 32).toInt().nextPowerOf(2)
                else blocksPerPiece
            blocks.write(ByteArray(leavesRequired * 32 - blocks.size.toInt()))
            pieces.write(blocks.piecesRoot())
        }
        val bytes = pieces.peek().readByteArray()
        return pieces.piecesRoot() to bytes
    }

    private fun Buffer.piecesRoot(): ByteArray {
        val hashNum = (size / 32).toInt()
        if (!hashNum.isPowerOf(2)) {
            repeat(hashNum.nextPowerOf(2) - hashNum) {
                write(paddingBytes)
            }
        }
        while (request(64)) {
            while (request(64)) {
                piecesRootTemp1.write(readByteArray(64).sha256())
            }
            write(piecesRootTemp1, piecesRootTemp1.size)
        }
        return readByteArray()
    }

    private inline fun ByteArray.piecesRoot() =
        piecesRootTemp2.apply { write(this@piecesRoot) }.piecesRoot()

    private inline fun ByteArray.sha256(): ByteArray {
        digester.update(this)
        return digester.digest()
    }
}

/**
 * 使用 BitTorrent V1 算法计算字节序列的 Pieces 信息摘要。
 * 若输入字节序列为空，则返回空字节序列
 */
inline fun ByteArray.bt1(pieceLength: Int = 256 * 1024, padding: Boolean = false) =
    BitTorrentV1Digest(pieceLength).apply { update(this@bt1) }.digest(padding)

/**
 * 使用 BitTorrent V2 算法计算字节序列的 Pieces Root 与 Pieces 信息摘要。
 * 若输入字节序列为空，则返回空字节序列
 */
inline fun ByteArray.bt2(pieceLength: Int = 256 * 1024) =
    BitTorrentV2Digest(pieceLength).apply { update(this@bt2) }.digest()
