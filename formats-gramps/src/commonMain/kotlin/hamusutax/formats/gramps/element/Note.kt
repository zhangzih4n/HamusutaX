package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("note")
data class Note(
    val handle: String, // _b39feb55e1173f4a699
    val change: String, // 1696446481
    val id: String, // N0010
    /**
     * 类别
     */
    val type: String, // Source text
    /**
     * 注释
     */
    val format: String? = null, // 1
    @XmlElement(true) val text: String,
    @XmlElement(true) val style: Style? = null,
    @XmlElement(true) @SerialName("tagref") val tagRef: TagRef? = null,
)
