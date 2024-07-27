package hamusutax.number

actual fun Int.toBinComplement(): String {
    val num = if (this >= 0) this.toUInt() else (-this).toUInt().inv() + 1u
    return num.toString(2).padStart(32, '0')
}
