package hamusutax.formats.bencode

import hamusutax.text.decodeToStringOrHexString
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonUnquotedLiteral
import kotlin.text.HexFormat.Companion.UpperCase

fun BencodeElement.toJsonElement(
    transform: (BencodeByteString) -> String = { it.bytes.decodeToStringOrHexString("<hex>", "</hex>", UpperCase) }
): JsonElement = when (this) {
    is BencodeByteString -> JsonPrimitive(transform(this))
    is BencodeInteger -> JsonUnquotedLiteral(content)
    is BencodeList -> JsonArray(map { it.toJsonElement() })
    is BencodeDictionary -> JsonObject(
        entries.associate { (key, value) ->
            transform(key) to value.toJsonElement()
        }
    )
}
