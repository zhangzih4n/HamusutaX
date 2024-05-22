@file:Suppress("UNUSED")
package hamusutax.core.io.path

import hamusutax.jvm.io.path.javaPath
import kotlinx.io.files.Path
import kotlin.io.path.createLinkPointingTo

actual fun Path.createLinkPointingTo(target: Path) {
    javaPath.createLinkPointingTo(target.javaPath)
}
