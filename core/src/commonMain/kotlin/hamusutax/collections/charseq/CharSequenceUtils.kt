package hamusutax.collections.charseq

fun CharSequence.mapChar(transform: (Char) -> Char) =
    map(transform).joinToString("")
