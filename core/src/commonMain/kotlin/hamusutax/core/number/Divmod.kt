@file:Suppress("UNUSED")
package hamusutax.core.number

infix fun Int.divmod(divisor: Int) =
    this / divisor to this % divisor

infix fun Long.divmod(divisor: Long) =
    this / divisor to this % divisor
