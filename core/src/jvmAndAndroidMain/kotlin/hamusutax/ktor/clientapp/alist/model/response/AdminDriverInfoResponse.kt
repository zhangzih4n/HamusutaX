package hamusutax.ktor.clientapp.alist.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminDriverInfoResponse(
    val common: List<Common>,
    val additional: List<Additional>,
    val config: Config
) {
    @Serializable
    data class Common(
        val name: String,
        val type: String,
        val default: String,
        val options: String,
        val required: Boolean,
        val help: String
    )

    @Serializable
    data class Additional(
        val name: String,
        val type: String,
        val default: String,
        val options: String,
        val required: Boolean,
        val help: String
    )

    @Serializable
    data class Config(
        val name: String,
        @SerialName("local_sort") val localSort: Boolean,
        @SerialName("only_local") val onlyLocal: Boolean,
        @SerialName("only_proxy") val onlyProxy: Boolean,
        @SerialName("no_cache") val noCache: Boolean,
        @SerialName("no_upload") val noUpload: Boolean,
        @SerialName("need_ms") val needMs: Boolean,
        @SerialName("default_root") val defaultRoot: String,
        val alert: String
    )
}
