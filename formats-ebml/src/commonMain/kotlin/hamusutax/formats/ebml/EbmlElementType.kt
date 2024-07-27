package hamusutax.formats.ebml

enum class EbmlElementType(val abbr: Char) {
    MASTER('m'),
    BINARY('b'),
    FLOAT('f'),
    UNSIGNED_INTEGER('u'),
    SIGNED_INTEGER('i'),
    UNICODE_STRING('8'),
    ASCII_STRING('s'),
    DATE_AND_TIME('d')
}
