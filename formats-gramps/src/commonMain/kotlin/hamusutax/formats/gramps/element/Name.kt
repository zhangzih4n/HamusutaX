package hamusutax.formats.gramps.element

import hamusutax.formats.gramps.attribute.NameType
import hamusutax.formats.gramps.serializer.NameTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("name")
data class Name(
    /**
     * 姓名——曾用名序号
     */
    val alt: String? = null,
    /**
     * 类别，比如出生名、婚后名
     */
    @Serializable(with = NameTypeSerializer::class) @XmlElement(false) val type: NameType? = null,
    /**
     * 名
     */
    @XmlElement(true) val first: String? = null,
    /**
     * 称呼
     */
    @XmlElement(true) val call: String? = null,
    /**
     * 姓
     */
    @XmlElement(true) val surnameElements: List<Surname>,
    @XmlElement(true) @SerialName("citationref") val citationRefElements: List<CitationRef>,
    @XmlElement(true) @SerialName("noteref") val noteRefElements: List<NoteRef>,
    /**
     * 后缀，比如“Jr.”或者“Ⅲ”
     */
    @XmlElement(true) val suffix: String? = null,
    /**
     * 头衔，比如“Dr.”或者“Rev.”
     */
    @XmlElement(true) val title: String? = null,
    /**
     * 昵称
     */
    @XmlElement(true) val nick: String? = null,
    /**
     * 家庭昵称
     */
    @SerialName("familynick") @XmlElement(true) val familyNick: String? = null,
    /**
     * 日期
     */
    @SerialName("dateval") @XmlElement(true) val dateValElements: List<DateVal>
)
