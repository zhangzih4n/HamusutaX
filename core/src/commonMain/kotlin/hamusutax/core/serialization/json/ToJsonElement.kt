package hamusutax.core.serialization.json

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * 严格地转换为 [JsonElement]
 * @throws SerializationException 当类型不匹配时
 */
fun Any?.toJsonElement(): JsonElement = when (this) {
    null -> JsonNull
    is JsonElement -> this
    is Number -> JsonPrimitive(this)
    is String -> JsonPrimitive(this)
    is List<*> -> JsonArray(map { it.toJsonElement() })
    is Map<*, *> -> {
        val map = map { (key, value) ->
            if (key !is String) throw SerializationException()
            key to value.toJsonElement()
        }.toMap()
        JsonObject(map)
    }
    else -> throw SerializationException()
}

/**
 * 强制转换为 [JsonElement]，可通过 [keyAction] 与 [valueAction] 控制类型不匹配时如何转换
 */
fun Any?.toJsonElementForce(
    keyAction: (Any?) -> String = { it.toString() },
    valueAction: (Any) -> JsonElement = { JsonPrimitive(it.toString()) },
): JsonElement = when (this) {
    null -> JsonNull
    is JsonElement -> this
    is Number -> JsonPrimitive(this)
    is String -> JsonPrimitive(this)
    is List<*> -> JsonArray(map { it.toJsonElementForce(keyAction, valueAction) })
    is Map<*, *> -> {
        val map = map { (key, value) ->
            keyAction(key) to value.toJsonElementForce(keyAction, valueAction)
        }.toMap()
        JsonObject(map)
    }
    else -> valueAction(this)
}
