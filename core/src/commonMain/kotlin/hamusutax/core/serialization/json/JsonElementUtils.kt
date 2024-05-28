package hamusutax.core.serialization.json

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

operator fun JsonElement.get(key: String): JsonElement? {
    if (this !is JsonObject) return null
    return jsonObject[key]
}

operator fun JsonElement.get(key: Int): JsonElement? {
    if (this !is JsonArray) return null
    return jsonArray[key]
}
