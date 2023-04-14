package `in`.rk585.notes.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import `in`.rk585.notes.ui.authentication.login.Login
import `in`.rk585.notes.ui.design.theme.NotesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesTheme {
                Login(Modifier.fillMaxSize())
            }
        }
    }
}
