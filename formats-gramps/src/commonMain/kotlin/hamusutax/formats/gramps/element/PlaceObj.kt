package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("placeobj")
data class PlaceObj(
    val handle: String, // _f3a1eff63bc16aca4a84b0b0094
    val change: String, // 1696446481
    val id: String, // P0000
    /**
     * 类别
     */
    val type: String, // Village
    /**
     * 地名
     */
    @XmlElement(true) @SerialName("ptitle") val placeTitle: String? = null,
    /**
     * 编码
     */
    @XmlElement(true) val code: String? = null,
    /**
     * 地名与地名其他名称
     */
    @XmlElement(true) @SerialName("pname") val placeName: List<PlaceName>,
    /**
     * 坐标（经度和纬度）
     */
    @XmlElement(true) val coord: Coord? = null,
    /**
     * 被此地围绕
     */
    @XmlElement(true) @SerialName("placeref") val placeRef: PlaceRef? = null,
    @XmlElement(true) @SerialName("objref") val objRef: ObjRef? = null,
    @XmlElement(true) @SerialName("citationref") val citationRef: CitationRef? = null,
    /**
     * 其他的地点
     */
    @XmlElement(true) @SerialName("location") val locationElements: List<Location>
)
