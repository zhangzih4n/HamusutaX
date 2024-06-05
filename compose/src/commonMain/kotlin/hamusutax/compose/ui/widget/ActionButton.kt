@file:Suppress("UNUSED")
package hamusutax.compose.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * 带有图标的无边框按钮
 */
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    textStyle: TextStyle = LocalTextStyle.current
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = colors
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = textStyle
            )
        }
    }
}
