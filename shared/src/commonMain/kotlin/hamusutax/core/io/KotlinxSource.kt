@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Buffer
import kotlinx.io.RawSource
import kotlinx.io.Source
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readByteArray

fun Source(file: Path) =
    SystemFileSystem.source(file)

fun Source(file: String) =
    SystemFileSystem.source(Path(file))

fun Sink(file: Path) =
    SystemFileSystem.sink(file)

fun Sink(file: String) =
    SystemFileSystem.sink(Path(file))

/**
 * 从此源中删除至少 1 个，最多 [byteCount] 个字节，并将其添加到 [sink]。
 * 返回读取的字节数，如果该源已耗尽，则返回 -1。
 *
 * @param sink 要写入数据的目标。
 * @param byteCount 要读取的字节数。
 *
 * @throws IllegalArgumentException 当 [byteCount] 为负数时。
 * @throws IllegalStateException 当源关闭时。
 *
 * @sample kotlinx.io.samples.KotlinxIoCoreCommonSamples.readAtMostToSink
 */
fun RawSource.readAtMostTo(sink: Buffer, byteCount: Int) =
    readAtMostTo(sink, byteCount.toLong())

fun Buffer.isEmpty() = size == 0L

fun Buffer.isNotEmpty() = size != 0L

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

/**
 * 填充缓冲区并读取字节，
 * 与 `InputStream.readNBytes` 相似
 *
 * @param byteCount 要读取的字节数。
 */
fun Source.readNBytes(byteCount: Long): ByteArray {
    request(byteCount)
    return readAtMost(byteCount)
}
