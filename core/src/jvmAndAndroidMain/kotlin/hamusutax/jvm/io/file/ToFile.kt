@file:Suppress("UNUSED")
package hamusutax.jvm.io.file

import java.io.File

val kotlinx.io.files.Path.javaFile: File
    get() = File(toString())
