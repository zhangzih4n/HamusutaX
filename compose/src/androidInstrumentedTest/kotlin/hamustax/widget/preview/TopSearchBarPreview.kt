package hamustax.widget.preview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hamusutax.compose.ui.widget.TopSearchAppBar

@Preview
@Composable
fun TopSearchAppBarPreview() {
    TopSearchAppBar(
        { Text(text = "搜索文本") },
        {
            IconButton(
                onClick = { /*TODO*/ },
                enabled = false
            ) {
                Icon(Icons.Outlined.Extension, null)
            }
        }
    )
}
