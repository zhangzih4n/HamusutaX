@file:Suppress("UNUSED")
package hamusutax.core.io.path

import hamusutax.jvm.io.path.toJavaPath
import kotlinx.io.files.Path
import kotlin.io.path.createLinkPointingTo

actual fun Path.createLinkPointingTo(target: Path) {
    toJavaPath().createLinkPointingTo(target.toJavaPath())
}
