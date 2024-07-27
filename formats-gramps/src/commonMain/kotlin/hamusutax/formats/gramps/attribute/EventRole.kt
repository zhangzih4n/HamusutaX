package hamusutax.formats.gramps.attribute

class EventRole(val value: String) {
    override fun toString() = "EventRole($value)"

    companion object {
        /**
         * 主要角色
         */
        val Primary = EventRole("Primary")

        /**
         * 家庭
         */
        val Family = EventRole("Family")
    }
}
