package hamusutax.core.collections

operator fun <K1, K2, V> MutableMap<K1, MutableMap<K2, V>>.set(key1: K1, key2: K2, value: V) {
    if (key1 in this) {
        this[key1]!![key2] = value
    } else {
        this[key1] = mutableMapOf(key2 to value)
    }
}

operator fun <K1, K2, K3, V> MutableMap<K1, MutableMap<K2, MutableMap<K3, V>>>.set(key1: K1, key2: K2, key3: K3, value: V) {
    if (key1 in this) {
        val value1 = this[key1]!!
        if (key2 in value1) {
            value1[key2]!![key3] = value
        } else {
            value1[key2] = mutableMapOf(key3 to value)
        }
    } else {
        this[key1] = mutableMapOf(key2 to mutableMapOf(key3 to value))
    }
}
