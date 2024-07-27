@file:Suppress("unused", "ConstPropertyName")
package hamusutax.terminal

/**
 * https://en.wikipedia.org/wiki/ANSI_escape_code
 */
class TerminalStyle {
    class ForegroundColor {
        companion object {
            inline fun color(n: Int) = code(38, n)
            inline fun color(red: Int, green: Int, blue: Int) =
                code(38, red, green, blue)

            const val Black = "\u001B[0m"
            const val Red = "\u001B[31m"
            const val Green = "\u001B[32m"
            const val Yellow = "\u001B[33m"
            const val Blue = "\u001B[34m"
            const val Magenta = "\u001B[35m"
            const val Cyan = "\u001B[36m"
            const val White = "\u001B[37m"
            const val Reset = "\u001B[39m"
            const val LightRed = "\u001B[91m"
            const val LightGreen = "\u001B[92m"
            const val LightYellow = "\u001B[93m"
            const val LightBlue = "\u001B[94m"
            const val LightMagenta = "\u001B[95m"
            const val LightCyan = "\u001B[96m"
            const val LightWhite = "\u001B[97m"
        }
    }

    class BackgroundColor {
        companion object {
            inline fun color(n: Int) = code(48, n)
            inline fun color(red: Int, green: Int, blue: Int) =
                code(48, red, green, blue)

            const val Black = "\u001B[40m"
            const val Red = "\u001B[41m"
            const val Green = "\u001B[42m"
            const val Yellow = "\u001B[43m"
            const val Blue = "\u001B[44m"
            const val Magenta = "\u001B[45m"
            const val Cyan = "\u001B[46m"
            const val White = "\u001B[47m"
            const val Reset = "\u001B[49m"
            const val LightRed = "\u001B[101m"
            const val LightGreen = "\u001B[102m"
            const val LightYellow = "\u001B[103m"
            const val LightBlue = "\u001B[104m"
            const val LightMagenta = "\u001B[105m"
            const val LightCyan = "\u001B[106m"
            const val LightWhite = "\u001B[107m"
        }
    }

    class UnderlineColor {
        companion object {
            inline fun color(n: Int) = code(58, n)
            inline fun color(red: Int, green: Int, blue: Int) =
                code(58, red, green, blue)

            val Black = color(0)
            val Red = color(160)
            val Green = color(40)
            val Yellow = color(184)
            val Blue = color(20)
            val Magenta = color(164)
            val Cyan = color(44)
            val White = color(255)
            const val Reset = "\u001B[59m"
        }
    }

    companion object {
        fun code(vararg n: Int) =
            "\u001B[${n.joinToString(";")}m"

        /**
         * 重置/正常
         *
         * 关闭所有属性。
         */
        const val Reset = "\u001B[0m"

        /**
         * 粗体或增加强度
         */
        const val Bold = "\u001B[1m"

        /**
         * 弱化（降低强度）
         *
         * 未广泛支持
         */
        const val Faint = "\u001B[2m"

        /**
         * 斜体	未广泛支持
         *
         * 有时视为反相显示
         */
        const val Italic = "\u001B[3m"

        /**
         * 下划线
         */
        const val Underline = "\u001B[4m"

        /**
         * 缓慢闪烁
         *
         * 低于每分钟150次
         */
        const val SlowBlink = "\u001B[5m"

        /**
         * 快速闪烁
         *
         * MS-DOS ANSI.SYS；每分钟150以上；未广泛支持
         */
        const val RapidBlink = "\u001B[6m"

        /**
         * 反显
         *
         * 前景色与背景色交换
         */
        const val ReverseVideo = "\u001B[7m"

        /**
         * 隐藏
         *
         * 未广泛支持
         */
        const val Conceal = "\u001B[8m"

        /**
         * 划除
         *
         * 字符清晰，但标记为删除。未广泛支持
         */
        const val CrossedOut = "\u001B[9m"

        /**
         * 主要（默认）字体
         */
        const val PrimaryFont = "\u001B[10m"

        const val AlternativeFont1 = "\u001B[11m"
        const val AlternativeFont2 = "\u001B[12m"
        const val AlternativeFont3 = "\u001B[13m"
        const val AlternativeFont4 = "\u001B[14m"
        const val AlternativeFont5 = "\u001B[15m"
        const val AlternativeFont6 = "\u001B[16m"
        const val AlternativeFont7 = "\u001B[17m"
        const val AlternativeFont8 = "\u001B[18m"
        const val AlternativeFont9 = "\u001B[19m"

        /**
         * 尖角体
         */
        const val Fraktur = "\u001B[20m"

        /**
         * 关闭粗体或双下划线
         *
         * 关闭粗体未广泛支持；双下划线几乎无支持
         */
        const val NotBold = "\u001B[21m"

        /**
         * 正常颜色或强度
         */
        const val NormalIntensity = "\u001B[22m"

        /**
         * 非斜体、非尖角体
         */
        const val NeitherItalic = "\u001B[23m"

        /**
         * 关闭下划线
         */
        const val NotUnderlined = "\u001B[24m"

        /**
         * 关闭闪烁
         */
        const val NotBlinking = "\u001B[25m"

        /**
         * 比例间距
         *
         * ITU T.61 和 T.416，没有已知终端使用
         */
        const val ProportionalSpacing = "\u001B[26m"

        /**
         * 关闭反显
         */
        const val NotReversed = "\u001B[27m"

        /**
         * 关闭隐藏
         */
        const val Reveal = "\u001B[28m"

        /**
         * 关闭划除
         */
        const val NotCrossedOut = "\u001B[29m"

        /**
         * 关闭比例间距
         *
         * T.61 和 T.416
         */
        const val DisableProportionalSpacing = "\u001B[50m"
        const val Framed = "\u001B[51m"
        const val Encircled = "\u001B[52m"

        /**
         * 上划线
         */
        const val Overlined = "\u001B[53m"
        const val NeitherFramedNorEncircled	= "\u001B[54m"

        /**
         * 关闭上划线
         */
        const val NotOverlined = "\u001B[55m"
        const val DefaultUnderlineColor = "\u001B[59m"

        /**
         * 表意文字下划线或右边线
         *
         * 几乎无支持
         */
        const val IdeogramUnderline = "\u001B[60m"

        /**
         * 表意文字双下划线或双右边线
         *
         * 几乎无支持
         */
        const val IdeogramDoubleUnderline = "\u001B[61m"

        /**
         * 表意文字上划线或左边线
         *
         * 几乎无支持
         */
        const val IdeogramOverline = "\u001B[62m"

        /**
         * 表意文字双上划线或双左边线
         *
         * 几乎无支持
         */
        const val IdeogramDoubleOverline = "\u001B[63m"

        /**
         * 表意文字着重标志
         *
         * 几乎无支持
         */
        const val IdeogramStressMarking = "\u001B[64m"

        /**
         * 表意文字属性关闭
         *
         * 重置 `60`–`64` 的所有效果
         */
        const val NoIdeogramAttributes = "\u001B[65m"
        const val Superscript = "\u001B[73m"
        const val Subscript = "\u001B[74m"
        const val NeitherSuperscriptNorSubscript = "\u001B[75m"
    }
}
