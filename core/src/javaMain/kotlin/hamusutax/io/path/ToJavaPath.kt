@file:Suppress("unused")
package hamusutax.io.path

import hamusutax.io.file.toJavaFile
import java.nio.file.Path as JavaPath
import kotlinx.io.files.Path

fun Path.toJavaPath(): JavaPath = toJavaFile().toPath()

fun String.toJavaPath(): JavaPath = JavaPath.of(this)
