package hamusutax.formats.gramps.attribute

class Type(val value: String) {
    override fun toString() = "Type($value)"

    companion object {
        val Birth = Type("Birth")
        val Death = Type("Death")
        val Baptism = Type("Baptism")
        val Christening = Type("Christening")
        val Immi = Type("Immi")
        val Census = Type("Census")
        val Occupation = Type("Occupation")
        val Education = Type("Education")
        val Degree = Type("Degree")
        val Marriage = Type("Marriage")
        val Engagement = Type("Engagement")
        val Divorce = Type("Divorce")
    }
}
