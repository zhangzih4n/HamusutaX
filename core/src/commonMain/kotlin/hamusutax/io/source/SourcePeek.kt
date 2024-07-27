package hamusutax.io.source

import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.io.readCodePointValue
import kotlinx.io.readDecimalLong
import kotlinx.io.readDouble
import kotlinx.io.readDoubleLe
import kotlinx.io.readFloat
import kotlinx.io.readFloatLe
import kotlinx.io.readHexadecimalUnsignedLong
import kotlinx.io.readIntLe
import kotlinx.io.readLine
import kotlinx.io.readLongLe
import kotlinx.io.readShortLe
import kotlinx.io.readString
import kotlinx.io.readUByte
import kotlinx.io.readUInt
import kotlinx.io.readUIntLe
import kotlinx.io.readULong
import kotlinx.io.readULongLe
import kotlinx.io.readUShort
import kotlinx.io.readUShortLe

fun Source.peekByte() = peek().readByte()
fun Source.peekShort() = peek().readShort()
fun Source.peekShortLe() = peek().readShortLe()
fun Source.peekInt() = peek().readInt()
fun Source.peekIntLe() = peek().readIntLe()
fun Source.peekLong() = peek().readLong()
fun Source.peekLongLe() = peek().readLongLe()

fun Source.peekUByte() = peek().readUByte()
fun Source.peekUShort() = peek().readUShort()
fun Source.peekUShortLe() = peek().readUShortLe()
fun Source.peekUInt() = peek().readUInt()
fun Source.peekUIntLe() = peek().readUIntLe()
fun Source.peekULong() = peek().readULong()
fun Source.peekULongLe() = peek().readULongLe()

fun Source.peekFloat() = peek().readFloat()
fun Source.peekFloatLe() = peek().readFloatLe()
fun Source.peekDouble() = peek().readDouble()
fun Source.peekDoubleLe() = peek().readDoubleLe()

fun Source.peekByteArray() = peek().readByteArray()
fun Source.peekString() = peek().readString()
fun Source.peekDecimalLong() = peek().readDecimalLong()
fun Source.peekHexadecimalUnsignedLong() = peek().readHexadecimalUnsignedLong()
fun Source.peekLine() = peek().readLine()
fun Source.peekCodePointValue() = peek().readCodePointValue()

fun Source.peekByteAsChar() = peek().readByte().toInt().toChar()
