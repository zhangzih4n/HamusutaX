@file:Suppress("UNUSED")
package hamusutax.core.color

fun rgbToGray(red: Int, green: Int, blue: Int): Int {
    require(red in 0..255 && green in 0..255 && blue in 0..255)
    return (red * 0.299 + green * 0.587 + blue * 0.114).toInt()
}
