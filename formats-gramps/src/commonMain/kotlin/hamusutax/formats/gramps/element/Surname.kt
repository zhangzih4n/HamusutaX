package hamusutax.formats.gramps.element

import hamusutax.formats.gramps.attribute.SurnameDerivation
import hamusutax.formats.gramps.serializer.SurnameDerivationSerializer
import hamusutax.formats.gramps.serializer.SurnamePrimarySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("surname")
data class Surname(
    /**
     * 称谓
     */
    val prefix: String? = null,
    /**
     * 是否为首选姓
     */
    @Serializable(with = SurnamePrimarySerializer::class) @SerialName("prim") @XmlElement(false) val isPrimary: Boolean? = null,
    /**
     * 连词
     */
    val connector: String? = null,
    /**
     * 姓氏由来，比如“继承来的”或者是“取自父名”
     */
    @Serializable(with = SurnameDerivationSerializer::class) @XmlElement(false) val derivation: SurnameDerivation? = null,
    @XmlValue(true) val text: String
)
