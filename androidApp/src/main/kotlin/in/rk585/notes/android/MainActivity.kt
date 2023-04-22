package `in`.rk585.notes.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import `in`.rk585.notes.ui.authentication.login.LoginScreen
import `in`.rk585.notes.ui.design.theme.NotesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesTheme {
                Navigator(LoginScreen)
            }
        }
    }
}
