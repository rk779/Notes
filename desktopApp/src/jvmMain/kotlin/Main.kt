import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import `in`.rk585.notes.ui.authentication.login.Login
import `in`.rk585.notes.ui.design.theme.NotesTheme

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Notes") {
        NotesTheme {
            Login(Modifier.fillMaxSize())
        }
    }
}
