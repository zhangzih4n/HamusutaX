@file:Suppress("UNUSED")
package hamusutax.jvm.io.path

import hamusutax.jvm.io.file.toJavaFile
import java.nio.file.Path

fun kotlinx.io.files.Path.toJavaPath(): Path = toJavaFile().toPath()
