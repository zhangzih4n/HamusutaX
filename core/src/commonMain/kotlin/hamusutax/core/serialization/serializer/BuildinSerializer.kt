@file:Suppress("UNUSED")
package hamusutax.core.serialization.serializer

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.serializer

val booleanSerializer = serializer<Boolean>()
val byteSerializer = serializer<Byte>()
val shortSerializer = serializer<Short>()
val intSerializer = serializer<Int>()
val longSerializer = serializer<Long>()
val floatSerializer = serializer<Float>()
val doubleSerializer = serializer<Double>()
val charSerializer = serializer<Char>()
val stringSerializer = serializer<String>()
val byteArraySerializer = serializer<ByteArray>()
val uByteSerializer = serializer<UByte>()
val uIntSerializer = serializer<UInt>()
val uLongSerializer = serializer<ULong>()
val uByteArraySerializer = serializer<UByteArray>()
