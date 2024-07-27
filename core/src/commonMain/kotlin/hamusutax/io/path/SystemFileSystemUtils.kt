package hamusutax.io.path

import hamusutax.io.path.PathWalkOption.BREADTH_FIRST
import kotlinx.io.files.FileNotFoundException
import kotlinx.io.files.FileSystem
import kotlinx.io.files.Path

fun FileSystem.deleteRecursively(path: Path, mustExist: Boolean = true) {
    if (mustExist && !path.exists()) throw FileNotFoundException("$path")

    val queue = ArrayDeque(listOf(path))
    while (queue.isNotEmpty()) {
        val first = queue.first()
        metadataOrNull(first)?.let {
            if (it.isDirectory) {
                val list = list(first)
                if (list.isEmpty()) {
                    delete(first)
                    queue.removeFirst()
                } else queue.addAll(0, list)
            }
            if (it.isRegularFile) {
                delete(first)
                queue.removeFirst()
            }
        }
    }
}

/**
 * 当前参数只支持 [PathWalkOption.BREADTH_FIRST]
 */
fun FileSystem.walk(path: Path, vararg options: PathWalkOption): Sequence<Path> = sequence {
    if (BREADTH_FIRST in options) {
        // 广度优先
        val queue = ArrayDeque(listOf(path))
        while (queue.isNotEmpty()) {
            val first = queue.removeFirst()
            metadataOrNull(first)?.let {
                if (it.isDirectory)
                    queue.addAll(list(first))
                if (it.isRegularFile)
                    yield(first)
            }
        }
    } else {
        // 深度优先
        val queue = ArrayDeque(listOf(path))
        while (queue.isNotEmpty()) {
            val first = queue.removeFirst()
            metadataOrNull(first)?.let {
                if (it.isDirectory)
                    queue.addAll(0, list(first))
                if (it.isRegularFile)
                    yield(first)
            }
        }
    }
}

enum class PathWalkOption {
    BREADTH_FIRST
}
