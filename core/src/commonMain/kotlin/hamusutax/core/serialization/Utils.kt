package hamusutax.core.serialization

import kotlinx.serialization.encoding.Decoder

inline fun <reified T : Decoder> Decoder.asDecoder(): T = this as? T
    ?: throw IllegalStateException(
        "Expected Decoder to be ${T::class.simpleName}, got ${this::class}"
    )
