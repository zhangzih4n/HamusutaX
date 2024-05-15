package hamusutax.composeapp

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun TestScreen() {
    val coroutineScope = rememberCoroutineScope()
    val animatableRotation = remember { Animatable(0F) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("123456")
            Image(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "Caret down icon",
                modifier = Modifier.rotate(animatableRotation.value)
                    .clickable {
                        coroutineScope.launch {
                            animatableRotation.animateTo(if (animatableRotation.value == 0F) 180F else 0F)
                        }
                    }
            )
        }
    }
}


@Preview
@Composable
fun TestScreenPreview() {
    MaterialTheme {
        Surface {
            TestScreen()
        }
    }
}
