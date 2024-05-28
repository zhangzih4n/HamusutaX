@file:Suppress("UNUSED")
package hamusutax.core.io.encoding

import hamusutax.core.datetime.isValidDate
import kotlinx.datetime.LocalDate

class IdentificationNumber(private val number: String) {
    init {
        require(number.isIdNumber())
    }

    private var isValidCache: Boolean? = null
    fun isValid(): Boolean {
        isValidCache?.let { return it }
        return number.isValidIdNumber().also { isValidCache = it }
    }

    val addressCode = number.slice(0..<6).toInt()
    val provinceCode = number.slice(0..<2).toInt()
    val cityCode = number.slice(2..<4).toInt()
    val countyCode = number.slice(4..<6).toInt()
    val birthYear = number.slice(6..<10).toInt()
    val birthMonthNumber = number.slice(10..<12).toInt()
    val birthDayOfMonth = number.slice(12..<14).toInt()
    val sequenceCode = number.slice(14..<17).toInt()
    val checkCode = number[17]

    val gender = if (number[16].digitToInt() % 2 == 0) Gender.FEMALE else Gender.MALE
    fun isMale() = gender == Gender.MALE
    fun isFemale() = gender == Gender.FEMALE

    val birthDate by lazy { LocalDate(birthYear, birthMonthNumber, birthDayOfMonth) }

    override fun toString() = number
    override fun hashCode() = IdentificationNumber::class.hashCode() + number.hashCode()
    override fun equals(other: Any?): Boolean {
        if (other !is IdentificationNumber) return false
        if (number == other.number) return true
        return false
    }

    companion object {
        private val digit = "0123456789".toCharArray()
        private val identificationDigit = "0123456789X".toCharArray()
        private val coefficient = listOf(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2)

        fun Char.isIdDigit() = this in identificationDigit

        fun String.isIdNumber(): Boolean {
            if (length != 18) return false
            return slice(0..<17).all { it in digit } && get(17) in identificationDigit
        }

        fun String.isValidIdNumber(): Boolean {
            if (!isIdNumber()) return false

            val birthYear = slice(6..<10).toInt()
            val birthMonthNumber = slice(10..<12).toInt()
            val birthDayOfMonth = slice(12..<14).toInt()
            if (!isValidDate(birthYear, birthMonthNumber, birthDayOfMonth)) return false

            val checkDigit = slice(0..<17)
                .mod_11_2()
                .let { if (it == 10) 'X' else it.toString().first() }
            return checkDigit == get(17)
        }
    }
}

enum class Gender {
    UNKNOWN, MALE, FEMALE, OTHER
}
