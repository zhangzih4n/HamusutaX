@file:Suppress("unused", "SpellCheckingInspection")
package hamusutax.jna.win32

class WinUser {
    companion object {
        const val CW_USEDEFAULT = 0x80000000L.toInt()

        const val CS_VREDRAW  = 0x0001
        const val CS_HREDRAW = 0x0002
        const val CS_DBLCLKS  = 0x0008
        const val CS_OWNDC = 0x0020
        const val CS_CLASSDC  = 0x0040
        const val CS_PARENTDC = 0x0080
        const val CS_NOCLOSE  = 0x0200
        const val CS_SAVEBITS = 0x0800
        const val CS_BYTEALIGNCLIENT = 0x1000
        const val CS_BYTEALIGNWINDOW = 0x2000
        const val CS_IME = 0x00010000
        const val CS_DROPSHADOW = 0x00020000 // require _WIN32_WINNT >= 0x0501
    }
}
