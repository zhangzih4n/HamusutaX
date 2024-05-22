package hamusutax.core.formats.ebml.define

import hamusutax.core.formats.ebml.EbmlElementDefine
import hamusutax.core.formats.ebml.EbmlElementType.ASCII_STRING
import hamusutax.core.formats.ebml.EbmlElementType.BINARY
import hamusutax.core.formats.ebml.EbmlElementType.MASTER
import hamusutax.core.formats.ebml.EbmlElementType.UNSIGNED_INTEGER

val defaultDefines = listOf(
    /**
     * Extensible Binary Meta Language
     *
     * 可扩展的二进制元语言
     */
    EbmlElementDefine(id = 0x1A45DFA3, name = "EBML", type = MASTER),
    /**
     * 此 EBML 版本
     */
    EbmlElementDefine(id = 0x4286, name = "EBMLVersion", type = UNSIGNED_INTEGER),
    /**
     * 支持读取此文件的最低 EBML 解析器版本
     */
    EbmlElementDefine(id = 0x42F7, name = "EBMLReadVersion", type = UNSIGNED_INTEGER),
    /**
     * 元素 ID 最大字节数
     */
    EbmlElementDefine(id = 0x42F2, name = "EBMLMaxIDLength", type = UNSIGNED_INTEGER),
    /**
     * 数据长度最大字节数
     */
    EbmlElementDefine(id = 0x42F3, name = "EBMLMaxSizeLength", type = UNSIGNED_INTEGER),
    /**
     * A name provided by an `EBML Schema` to designate a particular implementation of `EBML` for a data format (e.g., Matroska and WebM).
     *
     * 由 `EBML Schema` 提供的名称，用于指定数据格式的 `EBML` 的特定实现（例如，Matroska 和 WebM）。
     */
    EbmlElementDefine(id = 0x4282, name = "DocType", type = ASCII_STRING),
    EbmlElementDefine(id = 0x4287, name = "DocTypeVersion", type = UNSIGNED_INTEGER),
    EbmlElementDefine(id = 0x4285, name = "DocTypeReadVersion", type = UNSIGNED_INTEGER),
    EbmlElementDefine(id = 0x4281, name = "DocTypeExtension", type = MASTER),
    EbmlElementDefine(id = 0x4283, name = "DocTypeExtensionName", type = ASCII_STRING),
    EbmlElementDefine(id = 0x4284, name = "DocTypeExtensionVersion", type = UNSIGNED_INTEGER),

    /**
     * 标记损坏或空白数据，解析器会跳过此元素
     */
    EbmlElementDefine(id = 0xEC, name = "Void", type = BINARY),
    /**
     * 计算当前父元素的其它子元素的 CRC-32
     */
    EbmlElementDefine(id = 0xBF, name = "CRC-32", type = BINARY),

    EbmlElementDefine(id = 0x1B538667, name = "SignatureSlot", type = MASTER),
    /**
     * 签名算法（1=RSA，2=Elliptic）
     */
    EbmlElementDefine(id = 0x7E8A, name = "SignatureAlgo", type = UNSIGNED_INTEGER),
    /**
     * 签名散列算法（1=SHA1-160，2=MD5）
     */
    EbmlElementDefine(id = 0x7E9A, name = "SignatureHash", type = UNSIGNED_INTEGER),
    EbmlElementDefine(id = 0x7EA5, name = "SignaturePublicKey", type = BINARY),
    EbmlElementDefine(id = 0x7EB5, name = "Signature", type = BINARY),
    EbmlElementDefine(id = 0x7E5B, name = "SignatureElements", type = MASTER),
    EbmlElementDefine(id = 0x7E7B, name = "SignatureElementList", type = MASTER),
    EbmlElementDefine(id = 0x6532, name = "SignedElement", type = BINARY)
).associateBy { it.id }
