package hamusutax.formats.ebml.simple

import hamusutax.io.bytestring.toByteString
import hamusutax.text.isPrintableAscii
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline
import kotlin.text.HexFormat.Companion.UpperCase

sealed interface EbmlElement
sealed interface EbmlString : EbmlElement
sealed interface EbmlNumber : EbmlElement
sealed interface EbmlInteger : EbmlNumber

@Serializable
@JvmInline
value class EbmlBinary(private val value: ByteArray): EbmlElement {
    val bytes get() = value.toByteString()

    override fun toString() = "<hex>" + value.toHexString(UpperCase) + "</hex>"
}

@Serializable
@JvmInline
value class EbmlAsciiString(val value: String): EbmlString {
    init {
        require(value.isPrintableAscii()) { "字符串包含非 ASCII 可打印字符！($value)" }
    }
}

@Serializable
@JvmInline
value class EbmlUnicodeString(val value: String): EbmlString

@Serializable
@JvmInline
value class EbmlDateAndTime(val value: Instant): EbmlElement

@Serializable
@JvmInline
value class EbmlSignedInteger(val value: Long) : EbmlInteger

@Serializable
@JvmInline
value class EbmlUnsignedInteger(val value: ULong) : EbmlInteger

@Serializable
@JvmInline
value class EbmlFloat(val value: Double) : EbmlNumber

@Serializable
@JvmInline
value class EbmlMaster(val value: Long) : EbmlElement
