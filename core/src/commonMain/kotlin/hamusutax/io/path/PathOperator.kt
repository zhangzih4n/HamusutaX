package hamusutax.io.path

import kotlinx.io.files.Path
import kotlinx.io.files.SystemPathSeparator

operator fun Path.div(other: Path) =
    Path("$this$SystemPathSeparator$other")

operator fun Path.div(other: String) =
    Path("$this$SystemPathSeparator$other")

operator fun String.div(other: Path) =
    Path("$this$SystemPathSeparator$other")

operator fun Path.plus(other: String) =
    Path("$this$other")
