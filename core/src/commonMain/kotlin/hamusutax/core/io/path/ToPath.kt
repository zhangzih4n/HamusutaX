@file:Suppress("UNUSED")
package hamusutax.core.io.path

import kotlinx.io.files.Path

fun String.toPath() =
    Path(this)
