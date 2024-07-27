package hamusutax.clients.alist.model.response

import hamusutax.clients.alist.model.serializer.AlistDateTimeSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class FsDirsResponseItem(
    val name: String,
    @Serializable(AlistDateTimeSerializer::class) val modified: Instant
)
