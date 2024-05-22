@file:Suppress("UNUSED")
package hamusutax.core.io.buffer

import kotlinx.io.Buffer
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmName

fun buildBuffer(builderAction: Buffer.() -> Unit): Buffer {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Buffer().apply(builderAction)
}

@JvmName("byteArrayToBuffer")
fun ByteArray.toBuffer() =
    Buffer().apply { write(this@toBuffer) }

@JvmName("byteArrayIterableToBuffer")
fun Iterable<ByteArray>.toBuffer() =
    Buffer().apply { forEach { write(it) } }

@JvmName("byteArraySequenceToBuffer")
fun Sequence<ByteArray>.toBuffer() =
    Buffer().apply { forEach { write(it) } }

@JvmName("byteIterableToBuffer")
fun Iterable<Byte>.toBuffer() =
    Buffer().apply { forEach { writeByte(it) } }

@JvmName("byteSequenceToBuffer")
fun Sequence<Byte>.toBuffer() =
    Buffer().apply { forEach { writeByte(it) } }
