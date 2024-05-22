@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Buffer
import kotlinx.io.RawSource

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
