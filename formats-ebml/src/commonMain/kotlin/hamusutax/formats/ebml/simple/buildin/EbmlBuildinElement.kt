package hamusutax.formats.ebml.simple.buildin

import hamusutax.formats.ebml.EbmlElementType
import kotlinx.serialization.Serializable

@Serializable
data class EbmlBuildinElement(
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
