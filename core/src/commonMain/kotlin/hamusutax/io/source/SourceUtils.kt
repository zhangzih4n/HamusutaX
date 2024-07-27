@file:Suppress("unused")
package hamusutax.io.source

import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.readByteArray

fun Source.request(byteCount: Int) =
    request(byteCount.toLong())

fun Source.require(byteCount: Int) =
    require(byteCount.toLong())

/**
 * 读取至多 [byteCount] 个字节，
 * 缓冲区大小可能会耗尽，
 * 需要使用 [Source.request] 填充缓冲区。
 *
 * 由于每次都需要创建新 [Buffer]，
 * 此方法性能低于 [Source.readAtMostTo]
 *
 * @param byteCount 要读取的字节数。
 */
fun Source.readAtMost(byteCount: Long): ByteArray {
    val buffer = Buffer()
    readAtMostTo(buffer, byteCount)
    return buffer.readByteArray()
}

fun Source.readByteAsChar() = readByte().toInt().toChar()
