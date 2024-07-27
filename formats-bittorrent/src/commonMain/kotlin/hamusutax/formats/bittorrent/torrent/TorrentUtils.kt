@file:Suppress("unused")
package hamusutax.formats.bittorrent.torrent

import kotlinx.io.files.Path
import kotlinx.io.files.SystemPathSeparator

fun Torrent.Info.File.isSymlink() =
    attr?.contains('l') == true

fun Torrent.Info.File.isExecutable() =
    attr?.contains('x') == true

fun Torrent.Info.File.isHidden() =
    attr?.contains('h') == true

fun Torrent.Info.File.isPaddingFile() =
    attr?.contains('p') == true

fun padFileOf(length: Long) =
    Torrent.Info.File(
        attr = "p",
        length = length,
        path = listOf(".pad", length.toString())
    )

/**
 * @param path 符号链接本身的位置
 * @param symlinkPath 符号链接的目标
 */
fun symlinkFileOf(path: List<String>, symlinkPath: List<String>) =
    Torrent.Info.File(
        attr = "l",
        length = 0,
        path = path,
        symlinkPath = symlinkPath
    )

/**
 * @param path 符号链接本身的位置
 * @param symlinkPath 符号链接的目标
 */
fun symlinkFileOf(path: Path, symlinkPath: Path) =
    Torrent.Info.File(
        attr = "l",
        length = 0,
        path = path.toString().split(SystemPathSeparator),
        symlinkPath = symlinkPath.toString().split(SystemPathSeparator)
    )
