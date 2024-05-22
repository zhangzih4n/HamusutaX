package hamusutax.core.formats.ebml

import kotlinx.serialization.Serializable

@Serializable
data class EbmlElementDefine(
    /**
     * The Element ID displayed as octets
     */
    val id: Long,
    /**
     * The full name of the described element
     */
    val name: String,
    /**
     * The element's type
     */
    val type: EbmlElementType
)
