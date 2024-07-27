package hamusutax.formats.gramps.attribute

class SurnameDerivation(val value: String) {
    override fun toString() = "SurnameDerivation($value)"

    companion object {
        /**
         * 赐姓
         */
        val Given =
            SurnameDerivation("Given")

        /**
         * 继承父母的姓
         */
        val Inherited =
            SurnameDerivation("Inherited")

        /**
         * 继承父姓
         */
        val Patrilineal =
            SurnameDerivation("Patrilineal")

        /**
         * 继承母姓
         */
        val Matrilineal =
            SurnameDerivation("Matrilineal")

        /**
         * 假姓
         */
        val Pseudonym =
            SurnameDerivation("Pseudonym")

        /**
         * 源于父名的姓
         */
        val Patronymic =
            SurnameDerivation("Patronymic")

        /**
         * 源于母名的姓
         */
        val Matronymic =
            SurnameDerivation("Matronymic")

        /**
         * 受封的姓
         */
        val Feudal =
            SurnameDerivation("Feudal")

        /**
         * 未知
         */
        val Unknown =
            SurnameDerivation("Unknown")

        /**
         * 位置
         */
        val Location =
            SurnameDerivation("Location")

        /**
         * 职业
         */
        val Occupation =
            SurnameDerivation("Occupation")

        /**
         * 自选的姓
         */
        val Taken =
            SurnameDerivation("Taken")
    }
}
