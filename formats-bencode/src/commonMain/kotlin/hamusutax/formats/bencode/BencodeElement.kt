@file:Suppress("unused")
package hamusutax.formats.bencode

import hamusutax.io.bytestring.toByteString
import hamusutax.text.decodeToStringOrHexString
import hamusutax.text.decodeToStringOrNull
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline
import kotlin.text.HexFormat.Companion.UpperCase

@Serializable(BencodeElementSerializer::class)
sealed interface BencodeElement {
    val bencodeInteger get() = this as? BencodeInteger
    val bencodeByteString get() = this as? BencodeByteString
    val bencodeList get() = this as? BencodeList
    val bencodeDictionary get() = this as? BencodeDictionary
}

@JvmInline
@Serializable(BencodeByteStringSerializer::class)
value class BencodeByteString(
    private val value: ByteArray
): BencodeElement {
    constructor(value: String): this(value.encodeToByteArray())
    constructor(value: ByteString): this(value.toByteArray())

    val bytes get() = value.toByteString()
    val stringOrNull get() = value.decodeToStringOrNull()

    override fun toString() =
        value.decodeToStringOrHexString("<hex>", "</hex>", UpperCase)
}

@JvmInline
@Serializable(BencodeIntegerSerializer::class)
value class BencodeInteger(
    private val value: String
): BencodeElement {
    constructor(value: Number) : this(value.toString())
    constructor(value: UByte) : this(value.toString())
    constructor(value: UShort) : this(value.toString())
    constructor(value: UInt) : this(value.toString())
    constructor(value: ULong) : this(value.toString())

    val content get() = value
}

@JvmInline
@Serializable(BencodeListSerializer::class)
value class BencodeList(
    private val value: List<BencodeElement>
): BencodeElement, List<BencodeElement> by value

@JvmInline
@Serializable(BencodeDictionarySerializer::class)
value class BencodeDictionary(
    private val value: Map<BencodeByteString, BencodeElement>
): BencodeElement, Map<BencodeByteString, BencodeElement> by value
