@file:Suppress("unused")
package hamusutax.compose.ui.text

import androidx.compose.ui.text.TextLayoutResult

/**
 * 溢出文本的首字符偏移量，若未溢出则为 null
 */
val TextLayoutResult.overflowOffsetOrNull: Int?
    get() {
        if (!didOverflowHeight)
            return null
        val lastVisibleLine = getLineForVerticalPosition(size.height.toFloat())
        val lastVisibleLineEnd = getLineEnd(lastVisibleLine)
        return lastVisibleLineEnd
    }

/**
 * 溢出的文本，若未溢出则为 null
 */
val TextLayoutResult.overflowTextOrNull: String?
    get() {
        val offset = overflowOffsetOrNull ?: return null
        val text = layoutInput.text.text
        return text.substring(offset)
    }

/**
 * 溢出的文本，若未溢出则为空字符串
 */
val TextLayoutResult.overflowText
    get() = overflowTextOrNull ?: ""
