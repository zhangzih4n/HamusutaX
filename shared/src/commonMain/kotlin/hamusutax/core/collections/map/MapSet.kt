package hamusutax.core.collections.map

operator fun <K1, K2, V> MutableMap<K1, MutableMap<K2, V>>.set(key1: K1, key2: K2, value: V) {
    this[key1]?.let {
        it[key2] = value
        return
    }
    this[key1] = mutableMapOf(key2 to value)
}

operator fun <K1, K2, K3, V> MutableMap<K1, MutableMap<K2, MutableMap<K3, V>>>.set(key1: K1, key2: K2, key3: K3, value: V) {
    this[key1]?.let { value1 ->
        value1[key2]?.let { value2 ->
            value2[key3] = value
            return
        }
        value1[key2] = mutableMapOf(key3 to value)
        return
    }
    this[key1] = mutableMapOf(key2 to mutableMapOf(key3 to value))
}

operator fun <K1, K2, K3, K4, V> MutableMap<K1, MutableMap<K2, MutableMap<K3, MutableMap<K4, V>>>>.set(key1: K1, key2: K2, key3: K3, key4: K4, value: V) {
    this[key1]?.let { value1 ->
        value1[key2]?.let { value2 ->
            value2[key3]?.let { value3 ->
                value3[key4] = value
                return
            }
            value2[key3] = mutableMapOf(key4 to value)
            return
        }
        value1[key2] = mutableMapOf(key3 to mutableMapOf(key4 to value))
        return
    }
    this[key1] = mutableMapOf(key2 to mutableMapOf(key3 to mutableMapOf(key4 to value)))
}
