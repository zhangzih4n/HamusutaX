@file:Suppress("unused")
package hamusutax.formats.bencode

val BencodeInteger.byte get() = content.toByte()
val BencodeInteger.byteOrNull get() = content.toByteOrNull()
val BencodeInteger.short get() = content.toShort()
val BencodeInteger.shortOrNull get() = content.toShortOrNull()
val BencodeInteger.int get() = content.toInt()
val BencodeInteger.intOrNull get() = content.toIntOrNull()
val BencodeInteger.long get() = content.toLong()
val BencodeInteger.longOrNull get() = content.toLongOrNull()
val BencodeInteger.uByte get() = content.toUByte()
val BencodeInteger.uByteOrNull get() = content.toUByteOrNull()
val BencodeInteger.uShort get() = content.toUShort()
val BencodeInteger.uShortOrNull get() = content.toUShortOrNull()
val BencodeInteger.uInt get() = content.toUInt()
val BencodeInteger.uIntOrNull get() = content.toUIntOrNull()
val BencodeInteger.uLong get() = content.toULong()
val BencodeInteger.uLongOrNull get() = content.toULongOrNull()

fun List<BencodeElement>.toBencodeList() =
    BencodeList(this)

fun Map<BencodeByteString, BencodeElement>.toBencodeDictionary() =
    BencodeDictionary(this)
