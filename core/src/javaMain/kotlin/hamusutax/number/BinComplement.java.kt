package hamusutax.number

actual fun Int.toBinComplement(): String =
    Integer.toBinaryString(this).padStart(32, '0')
