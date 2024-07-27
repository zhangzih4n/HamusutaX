package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("reporef")
data class RepoRef(
    @SerialName("hlink") val hyperlink: String, // _a701e99f93e5434f6f3
    @SerialName("callno") val callNo: String, // CA-123-LL-456_Num/ber
    val medium: String // _a701e99f93e5434f6f3
)
