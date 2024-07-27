@file:Suppress("unused")
package hamusutax.collections.map

import kotlin.jvm.JvmName

fun List<Pair<List<String>, Any>>.toNestedMap(): Map<String, Any> {
    val result = mutableMapOf<String, Any>()
    forEach { (paths, value) ->
        if (paths.isEmpty())
            throw IllegalArgumentException("输入中存在缺失路径的值：$value")

        var currentMap = result
        paths.forEachIndexed { index, key ->
            if (index == paths.size - 1) currentMap[key] = value
            else {
                if (!currentMap.containsKey(key) || currentMap[key] !is MutableMap<*, *>)
                    currentMap[key] = mutableMapOf<String, Any>()

                @Suppress("UNCHECKED_CAST")
                currentMap = currentMap[key] as MutableMap<String, Any>
            }
        }
    }
    return result
}

@JvmName("toNestedNullableMap")
fun List<Pair<List<String>, Any?>>.toNestedMap(): Map<String, Any?> {
    val result = mutableMapOf<String, Any?>()
    forEach { (paths, value) ->
        if (paths.isEmpty())
            throw IllegalArgumentException("输入中存在缺失路径的值：$value")

        var currentMap = result
        paths.forEachIndexed { index, key ->
            if (index == paths.size - 1) currentMap[key] = value
            else {
                if (!currentMap.containsKey(key) || currentMap[key] !is MutableMap<*, *>)
                    currentMap[key] = mutableMapOf<String, Any?>()

                @Suppress("UNCHECKED_CAST")
                currentMap = currentMap[key] as MutableMap<String, Any?>
            }
        }
    }
    return result
}

fun Map<List<String>, Any>.toNestedMap() =
    toList().toNestedMap()

@JvmName("toNestedNullableMap")
fun Map<List<String>, Any?>.toNestedMap() =
    toList().toNestedMap()
