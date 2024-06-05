@file:Suppress("UNUSED")
package hamusutax.core.pythonic

private fun str(data: Any?): String =
    if (data == null) "None"
    else when (data) {
        is String -> "\"$data\""
        is List<*> -> data.joinToString(", ") { str(it) }
            .let { "[$it]" }
        is Map<*, *> -> data.map { (key, value) -> "${str(key)}: ${str(value)}" }
            .joinToString(", ")
            .let { "{$it}" }
        is ByteArray -> "b\"${data.decodeToString()}\""
        else -> data.toString()
    }

fun pyprint(data: Any?, end: String = "\n") = print(str(data) + end)
