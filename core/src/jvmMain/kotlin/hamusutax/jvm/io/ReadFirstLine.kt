@file:Suppress("UNUSED")
package hamusutax.jvm.io

import java.io.File
import java.nio.file.Path
import kotlin.io.path.bufferedReader

fun File.readFirstLine(): String =
    bufferedReader().readLine()

fun Path.readFirstLine(): String =
    bufferedReader().readLine()
