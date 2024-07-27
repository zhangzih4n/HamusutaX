package hamusutax.formats.gramps.element

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("database", "http://gramps-project.org/xml/1.7.1/")
data class Database(
    @XmlElement(true) val header: Header,
    @XmlElement(true) @SerialName("name-formats") val nameFormats: NameFormats? = null,
    @XmlElement(true) val tags: Tags? = null,
    @XmlElement(true) val events: Events? = null,
    @XmlElement(true) val people: People? = null,
    @XmlElement(true) val families: Families? = null,
    @XmlElement(true) val citations: Citations? = null,
    @XmlElement(true) val sources: Sources? = null,
    @XmlElement(true) val places: Places? = null,
    @XmlElement(true) val notes: Notes? = null,
    @XmlElement(true) val objects: Objects? = null,
    @XmlElement(true) val repositories: Repositories? = null,
    @XmlElement(true) val bookmarks: Bookmarks? = null,
    @XmlElement(true) val nameMaps: NameMaps? = null
)
