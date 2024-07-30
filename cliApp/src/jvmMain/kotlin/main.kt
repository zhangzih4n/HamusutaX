import hamusutax.crypto.hash.md5
import hamusutax.io.encoding.decodeFromBase64
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock.System.now
import kotlinx.serialization.json.Json

private fun main() = runBlocking {
    val time = now().toEpochMilliseconds()
    /*println(encryptedString1Decoded.decodeToString())
    println(encryptedString2Decoded.decodeToString())
    println(encryptedString1.decodeFromBase64().decodeToString().let { Json.decodeFromString<List<Int>>(it) })*/
    println(l.decodeToString())

    //val array = ByteArray(4)
    //"${array.joinToString("")}.${decryptedString1.decodeToString()}.${decryptedString2.decodeToString()}.${currentMicros}"
}

val l = intArrayOf(
    195, 142, 195, 177, 197, 163, 195, 169, 114, 195, 177, 195, 165, 197,
    163, 195, 174, 195, 182, 195, 177, 195, 165, 196, 188, 195, 174, 197,
    190, 195, 165, 197, 163, 195, 174, 225, 187, 157, 195, 177
).map { it.toUByte().toByte() }.toByteArray()

val decryptedString1 = byteArrayOf(-78, -37, 127, -95, -67, -94, 123, 103, -119, -46, 123, -37, -67, -77, 123, -54, -117, -106, -123, -96, 126, -49, -90, -105, -110, -97, -68, 100, -118, -120, -80, -95, -114, 103, -121, -96, -114, -81, -96, 104, -126, 121, 118, 106, -124, 124, -126, 104, -125, 121, 126, -83, -113, -116, -118, 104, -126, -97, 118, -81, -114, -97, -95, -97, -113, 124, 123, -95, -125, -119, -122, 101, -125, -81, -100, 101, -125, -81, -99, -99, -126, -119, -96, 106, -113, -119, -103, -96, -125, -116, 122, 103, -113, -119, 123, -99, -125, -119, -104, 103, -124, -119, 122, -83, -126, -97, -125, -97, -126, -116, 122, 106, -126, -81, 123, -97, -126, 121, -118, 104, -124, 121, -122, -82, -113, -94, 126, 104, -126, 103, 127, -99, -126, 103, 126, -81, -114, -81, -100, -81, -114, -94, -125, -96, -125, -97, -95, -97, -126, -119, -103, -97, -114, 103, -114, -83, -125, -81, -122, -84, -124, 121, 123, -95, -126, 103, -122, 105, -114, -116, 122, 114)

val decryptedString2 = byteArrayOf(-58, -87, 123, 106, -79, -90, -116, -94, -109, -67, -94, -37, -57, 122, 118, -98)

fun decodeKeyRaw(key: String): ByteArray {
    val listString = key.decodeFromBase64().decodeToString()
    return Json.decodeFromString<List<Int>>(listString)
        .map { it.toUByte().toByte() }
        .toByteArray()
}
