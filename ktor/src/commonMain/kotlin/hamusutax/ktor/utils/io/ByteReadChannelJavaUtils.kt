package hamusutax.ktor.utils.io

import hamusutax.io.path.sink
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.isEmpty
import io.ktor.utils.io.core.readBytes
import kotlinx.io.Sink
import kotlinx.io.files.Path

internal suspend inline fun ByteReadChannel.readTo(action: (ByteArray) -> Unit) {
    while (!isClosedForRead) {
        val packet = readRemaining(16 * 1024)
        while (!packet.isEmpty) {
            action(packet.readBytes())
        }
    }
}

suspend fun ByteReadChannel.readTo(sink: Sink) =
    readTo {
        sink.write(it)
    }

suspend inline fun ByteReadChannel.readTo(path: Path) =
    path.sink().let {
        readTo(it)
        it.flush()
    }
