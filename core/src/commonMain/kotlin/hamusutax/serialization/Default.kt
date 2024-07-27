package hamusutax.serialization

import kotlinx.serialization.json.Json

val JsonEncodeDefaults = Json { encodeDefaults = true }

val JsonPrettyPrint = Json { prettyPrint = true }

val JsonEncodeDefaultsPrettyPrint = Json {
    encodeDefaults = true
    prettyPrint = true
}
