package hamustax.widget.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hamusutax.compose.ui.widget.FormTextField
import hamusutax.compose.ui.widget.PasswordTextField

@Preview
@Composable
fun FormTextFieldPreview() {
    FormTextField("", {}, "用户名")
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField("", {}, "密码")
}
