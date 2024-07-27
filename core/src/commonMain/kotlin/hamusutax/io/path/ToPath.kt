@file:Suppress("unused")
package hamusutax.io.path

import kotlinx.io.files.Path

fun String.toPath() =
    Path(this)
