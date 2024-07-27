package hamusutax.cliApp

import com.sun.jna.platform.win32.Kernel32
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef.LRESULT
import com.sun.jna.platform.win32.WinUser
import com.sun.jna.platform.win32.WinUser.WNDCLASSEX
import com.sun.jna.platform.win32.WinUser.WindowProc
import hamusutax.jna.win32.WinUser.Companion.CS_HREDRAW
import hamusutax.jna.win32.WinUser.Companion.CS_VREDRAW
import hamusutax.jna.win32.WinUser.Companion.CW_USEDEFAULT

fun winMain() {
    val hInstance = Kernel32.INSTANCE.GetModuleHandle(null)
    with(User32.INSTANCE) {
        val wndClass = WNDCLASSEX()
        wndClass.style = CS_HREDRAW or CS_VREDRAW
        wndClass.lpfnWndProc = WndProc
        wndClass.cbClsExtra = 0
        wndClass.cbWndExtra = 0
        wndClass.hInstance = hInstance
        wndClass.lpszMenuName = null
        wndClass.lpszClassName = "MyWindowClass"

        if (RegisterClassEx(wndClass).toInt() == 0) {
            println("窗口类注册失败")
            return
        }

        val hWnd = CreateWindowEx(
            0,
            wndClass.lpszClassName,
            "Hello, JNA!",
            WinUser.WS_OVERLAPPEDWINDOW,
            CW_USEDEFAULT, CW_USEDEFAULT,
            1200, 900,
            null,
            null,
            hInstance,
            null
        )

        if (hWnd == null) {
            println("窗口创建失败")
            return
        }

        ShowWindow(hWnd, WinUser.SW_SHOW)
        UpdateWindow(hWnd)

        val msg = WinUser.MSG()
        while (GetMessage(msg, null, 0, 0) != 0) {
            TranslateMessage(msg)
            DispatchMessage(msg)
        }
    }
}

val WndProc = WindowProc { hWnd, uMsg, wParam, lParam ->
    when (uMsg) {
        WinUser.WM_DESTROY -> {
            User32.INSTANCE.PostQuitMessage(0)
            LRESULT(0)
        }
        else -> User32.INSTANCE.DefWindowProc(hWnd, uMsg, wParam, lParam)
    }
}
