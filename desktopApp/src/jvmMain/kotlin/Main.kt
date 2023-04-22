import androidx.compose.ui.window.Window
import androidx.compose.ui.window.awaitApplication
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import `in`.rk585.notes.ui.authentication.login.LoginScreen
import `in`.rk585.notes.ui.authentication.navigation.authScreenModule
import `in`.rk585.notes.ui.design.theme.NotesTheme

suspend fun main() {

    ScreenRegistry {
        authScreenModule()
    }

    awaitApplication {
        Window(onCloseRequest = ::exitApplication, title = "Notes") {
            NotesTheme {
                Navigator(LoginScreen)
            }
        }
    }
}
