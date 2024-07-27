package hamusutax.formats.ebml

import kotlinx.serialization.SerialInfo

@SerialInfo
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
annotation class EbmlID(val id: Long)

@SerialInfo
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
annotation class EbmlCRC32(val value: Boolean = true)
