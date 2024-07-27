package hamustax.widget.preview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hamusutax.compose.ui.widget.ActionButton

@Preview
@Composable
fun ActionButtonPreview() {
    ActionButton("探索", Icons.Outlined.Explore)
}
