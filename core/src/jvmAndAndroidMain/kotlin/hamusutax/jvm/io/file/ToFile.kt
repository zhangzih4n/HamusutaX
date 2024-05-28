@file:Suppress("UNUSED")
package hamusutax.jvm.io.file

import java.io.File

fun kotlinx.io.files.Path.toJavaFile() = File(toString())
