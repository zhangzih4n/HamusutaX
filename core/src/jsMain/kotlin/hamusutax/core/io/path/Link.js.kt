package hamusutax.core.io.path

import kotlinx.io.files.Path

actual fun Path.createLinkPointingTo(target: Path) {
    createLinkPointingTo(target)
}
