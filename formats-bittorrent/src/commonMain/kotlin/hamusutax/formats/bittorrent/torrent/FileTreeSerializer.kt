package hamusutax.formats.bittorrent.torrent

import hamusutax.formats.bencode.BencodeByteString
import hamusutax.formats.bencode.BencodeContentPolymorphicSerializer
import hamusutax.formats.bencode.BencodeDictionary
import hamusutax.formats.bencode.BencodeElement
import hamusutax.formats.bittorrent.torrent.Torrent.Info.FileTree
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.serializer

object FileTreeSerializer : BencodeContentPolymorphicSerializer<FileTree>(FileTree::class) {
    override fun selectDeserializer(element: BencodeElement): DeserializationStrategy<FileTree> {
        require(element is BencodeDictionary) { "expected BencodeDictionary, but got ${element::class} $element" }
        return when {
            BencodeByteString("") in element.keys -> serializer<FileTree.File>()
            else -> serializer<FileTree.Directory>()
        }
    }
}
