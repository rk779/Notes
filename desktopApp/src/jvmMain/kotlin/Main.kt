import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import `in`.rk585.notes.ui.navigation.Application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Notes") {
        Application()
    }
}
