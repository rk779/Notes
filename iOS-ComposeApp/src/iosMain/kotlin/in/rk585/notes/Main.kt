package `in`.rk585.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import `in`.rk585.notes.ui.authentication.login.Login

fun MainViewController() = ComposeUIViewController {
    Login(
        modifier = Modifier.fillMaxSize()
    )
}
