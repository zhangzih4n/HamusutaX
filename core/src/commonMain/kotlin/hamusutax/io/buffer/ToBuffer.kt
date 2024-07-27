@file:Suppress("unused")
package hamusutax.io.buffer

import kotlinx.io.Buffer
import kotlinx.io.bytestring.ByteString
import kotlinx.io.write
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmName

fun buildBuffer(builderAction: Buffer.() -> Unit): Buffer {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Buffer().apply(builderAction)
}

fun ByteArray.toBuffer() =
    Buffer().apply { write(this@toBuffer) }

fun ByteString.toBuffer() =
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
