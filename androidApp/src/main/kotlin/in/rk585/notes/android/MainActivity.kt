package `in`.rk585.notes.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import cafe.adriel.voyager.navigator.Navigator
import `in`.rk585.notes.android.inject.MainActivityComponent
import `in`.rk585.notes.android.inject.create
import `in`.rk585.notes.core.base.extensions.unsafeLazy
import `in`.rk585.notes.core.common.LocalViewModel
import `in`.rk585.notes.core.common.ViewModelComponent
import `in`.rk585.notes.ui.authentication.login.LoginScreen
import `in`.rk585.notes.ui.design.theme.NotesTheme

class MainActivity : ComponentActivity() {

    private lateinit var component: MainActivityComponent
    private val viewModels: ViewModelComponent by unsafeLazy { component.viewModels }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = MainActivityComponent::class.create(this)

        setContent {
            CompositionLocalProvider(
                LocalViewModel provides viewModels
            ) {
                NotesTheme {
                    Navigator(LoginScreen)
                }
            }
        }
    }
}
