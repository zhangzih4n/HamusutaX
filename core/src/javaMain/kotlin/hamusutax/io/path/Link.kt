@file:Suppress("unused")
package hamusutax.io.path

import kotlinx.io.files.Path
import kotlin.io.path.createLinkPointingTo

fun Path.createLinkPointingTo(target: Path) {
    toJavaPath().createLinkPointingTo(target.toJavaPath())
}
