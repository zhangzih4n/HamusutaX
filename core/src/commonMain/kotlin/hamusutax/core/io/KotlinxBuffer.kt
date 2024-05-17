@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Buffer
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

fun Buffer.isEmpty() = size == 0L

fun Buffer.isNotEmpty() = size != 0L

fun buildBuffer(builderAction: Buffer.() -> Unit): Buffer {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Buffer().apply(builderAction)
}
