package hamusutax.text

fun String.isAscii() = all { it.code in 0..255 }

fun String.isPrintableAscii() = all { it.code in 32..126 }

fun Char.isAscii() = code in 0..255

fun Char.isPrintableAscii() = code in 32..126
