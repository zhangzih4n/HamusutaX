package hamusutax.formats.gramps.attribute

class NameType(val value: String) {
    override fun toString() = "NameType($value)"

    companion object {
        /**
         * 出生名
         *
         * Name given at birth to a child, possibly recorded on a Birth certificate.
         */
        val BirthName =
            NameType("Birth Name")

        /**
         * 婚后名
         */
        val MarriedName =
            NameType("Married Name")

        /**
         * 未知
         *
         * Names needing more research to determine their derivation.
         */
        val Unknown = NameType("Unknown")

        /**
         * 又被称为
         *
         * An alias. Another name by which the person is recognized or acknowledged.
         */
        val AlsoKnownAs =
            NameType("Also Known As")
    }
}
