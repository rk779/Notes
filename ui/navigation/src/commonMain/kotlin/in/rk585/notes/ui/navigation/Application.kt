package `in`.rk585.notes.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import `in`.rk585.notes.ui.authentication.login.LoginScreen
import `in`.rk585.notes.ui.design.theme.NotesTheme

@Composable
fun Application() {
    NotesTheme {
        Navigator(
            screen = LoginScreen
        )
    }
}
