package hamusutax.formats.gramps.attribute

class AttributeType(val value: String) {
    override fun toString() = "AttributeType($value)"

    companion object {
        /**
         * 子女数
         */
        val NumberOfChildren =
            AttributeType("Number of Children")

        /**
         * 身份证号
         */
        val IdentificationNumber =
            AttributeType("Identification Number")

        /**
         * 父亲的年龄
         */
        val FatherAge =
            AttributeType("Father Age")

        /**
         * 母亲的年龄
         */
        val MotherAge =
            AttributeType("Mother Age")

        /**
         * 昵称
         */
        val Nickname =
            AttributeType("Nickname")

        /**
         * 年龄
         */
        val Age = AttributeType("Age")

        /**
         * 年线
         */
        val Time = AttributeType("Time")

        /**
         * 社会安全号
         */
        val SocialSecurityNumber =
            AttributeType("Social Security Number")

        /**
         * 未知
         */
        val Unknown =
            AttributeType("Unknown")

        /**
         * 原国籍
         */
        val NationalOrigin =
            AttributeType("National Origin")

        /**
         * 原因
         */
        val Cause = AttributeType("Cause")

        /**
         * 证物，证人
         */
        val Witness =
            AttributeType("Witness")

        /**
         * 职业
         */
        val Occupation =
            AttributeType("Occupation")

        /**
         * 中介
         */
        val Agency = AttributeType("Agency")

        /**
         * 种姓，阶级
         */
        val Caste = AttributeType("Caste")
    }
}
