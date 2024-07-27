package hamusutax.formats.gramps.element

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("created")
data class Created(
    val date: String, // 2023-11-02
    val version: String // AIO64-5.1.6-1
)
