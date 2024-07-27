@file:Suppress("unused")
package hamusutax.formats.bencode

internal val INTEGER_FIRST_CHAR_CHARSET = ('0'..'9') + '-'
internal val INTEGER_CHARSET = '0'..'9'

internal const val INTEGER_START_CHAR = 'i'
internal const val INTEGER_START = INTEGER_START_CHAR.toString()
internal const val LIST_START_CHAR = 'l'
internal const val LIST_START = LIST_START_CHAR.toString()
internal const val DICTIONARY_START_CHAR = 'd'
internal const val DICTIONARY_START = DICTIONARY_START_CHAR.toString()

internal const val END_CHAR = 'e'
internal const val END = END_CHAR.toString()

internal const val STRING_SEPARATOR_CHAR = ':'
internal const val STRING_SEPARATOR = STRING_SEPARATOR_CHAR.toString()
