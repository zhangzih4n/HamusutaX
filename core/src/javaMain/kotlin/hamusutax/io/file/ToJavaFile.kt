@file:Suppress("unused")
package hamusutax.io.file

import kotlinx.io.files.Path
import java.io.File

fun Path.toJavaFile() = File(toString())

fun String.toJavaFile() = File(this)
