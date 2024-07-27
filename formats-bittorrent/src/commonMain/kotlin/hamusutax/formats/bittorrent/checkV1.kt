package hamusutax.formats.bittorrent

/*
/**
 * 不进行填充时的哈希比对
 */
@Deprecated("TODO", level = DeprecationLevel.ERROR)
fun Torrent.checkV1(
    paths: List<Path>,
    pieceLength: Long,
    onEquals: (hash: ByteArray) -> Unit = {},
    onNotEquals: (expect: ByteArray, actual: ByteArray) -> Unit = { expect, actual -> throw Exception() }
) {
    val pieces = info.pieces!!
    val piecesList = pieces.chunked(20).toMutableList()
    val buffer = Buffer()
    paths.forEachIndexed { index, path ->
        val isLast = index == paths.size - 1
        if (!path.exists()) {
            println("\"$path\" notExists")
        } else {
            val source = path.source()
            while (true) {
                // 循环读取直到填满一个 Piece 或文件读完
                while (true) {
                    val request = pieceLength - buffer.size
                    source.request(request)

                    if (request == 0L || source.readAtMostTo(buffer, request) == -1L)
                        break
                }

                if ((buffer.size != pieceLength && !isLast) || buffer.size == 0L)
                    break

                // 开始计算 Piece
                val expect = piecesList.removeFirst()
                val actual = buffer.readByteArray().sha1()
                if (expect contentEquals actual) {
                    println(
                        "\"$path\"[${pieces.size / 20 - piecesList.size}] contentEquals -> ${
                            actual.toHexString(
                                HexFormat.UpperCase
                            )
                        }"
                    )
                    onEquals(expect)
                } else {
                    println(
                        "\"$path\"[${pieces.size / 20 - piecesList.size}] contentNotEquals -> expect: ${
                            expect.toHexString(
                                HexFormat.UpperCase
                            )
                        }, actual: ${actual.toHexString(HexFormat.UpperCase)}"
                    )
                    onNotEquals(expect, actual)
                }
            }
        }
    }
}
*/
