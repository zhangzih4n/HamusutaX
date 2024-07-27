@file:Suppress("unused")
package hamusutax.okhttp

import kotlinx.serialization.json.Json
import okhttp3.Response
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

inline fun buildResponse(builderAction: Response.Builder.() -> Unit): Response {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Response.Builder().apply(builderAction).build()
}

inline fun <reified T> Response.parseJsonAs(parser: Json = Json) =
    parser.decodeFromString<T>(body.string())
