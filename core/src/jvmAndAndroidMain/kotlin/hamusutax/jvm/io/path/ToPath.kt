@file:Suppress("UNUSED")
package hamusutax.jvm.io.path

import hamusutax.jvm.io.file.javaFile
import java.nio.file.Path

val kotlinx.io.files.Path.javaPath: Path
    get() = javaFile.toPath()
