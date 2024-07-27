package hamusutax.compose.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopSearchAppBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
    searchText: String = "",
    onSearchTextChange: (searchText: String) -> Unit = {},
    onSearch: (searchText: String) -> Unit = {},
    icon: ImageVector = Icons.Outlined.Search,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholder: String? = null
) {
    val focusRequester = remember { FocusRequester() }
    var isSearching by remember { mutableStateOf(searchText.isNotBlank()) }
    var searchText by remember { mutableStateOf(searchText) }

    TopAppBar(
        title = {
            Column {
                if (isSearching) {
                    val textStyle = TextStyle(fontSize = 18.sp)
                    BasicTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            onSearchTextChange(searchText)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        textStyle = textStyle,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = keyboardType,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(onSearch = { onSearch(searchText) }),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (searchText.isEmpty() && placeholder != null) {
                                Text(
                                    text = placeholder,
                                    color = Color.Gray,
                                    style = textStyle
                                )
                            }
                            innerTextField()
                        },
                    )
                } else title()
                Spacer(Modifier.padding(bottom = 2.dp))
            }
        },
        navigationIcon = {
            if (isSearching) {
                IconButton(onClick = {
                    isSearching = false
                    searchText = ""
                }) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, null)
                }
            } else navigationIcon()
        },
        actions = {
            if (!isSearching) {
                IconButton(onClick = { isSearching = true }) {
                    Icon(icon, null)
                }
            } else if (searchText.isNotBlank()) {
                IconButton(onClick = { searchText = "" }) {
                    Icon(Icons.Outlined.Close, null)
                }
            }
            actions()
        }
    )

    LaunchedEffect(Unit) {
        if (searchText.isNotBlank()) {
            onSearch(searchText)
        }
    }
}
