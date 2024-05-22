@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.files.Path

fun String.toPath() =
    Path(this)
