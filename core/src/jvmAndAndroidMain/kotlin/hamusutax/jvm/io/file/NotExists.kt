@file:Suppress("UNUSED")
package hamusutax.jvm.io.file

import java.io.File
import java.nio.file.LinkOption
import kotlin.io.path.notExists

fun File.notExists(vararg options: LinkOption): Boolean =
    toPath().notExists(*options)
