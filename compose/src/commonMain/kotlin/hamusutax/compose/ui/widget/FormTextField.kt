package hamusutax.compose.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = if (label == null) null else { { Text(text = label) } },
        trailingIcon = {
            IconButton(onClick = { onValueChange("") }) {
                Icon(Icons.Filled.Clear, null)
            }
        },
        isError = isError,
    )
}
