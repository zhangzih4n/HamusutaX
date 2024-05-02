@file:Suppress("UNUSED")
package hamusutax.io

import java.io.File
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path

fun Path.notExists(vararg options: LinkOption) =
    Files.notExists(this, *options)

fun File.notExists(vararg options: LinkOption) =
    toPath().notExists(*options)
