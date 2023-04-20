import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.awaitApplication
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import `in`.rk585.notes.core.base.extensions.unsafeLazy
import `in`.rk585.notes.core.common.LocalViewModel
import `in`.rk585.notes.inject.ApplicationComponent
import `in`.rk585.notes.inject.create
import `in`.rk585.notes.ui.authentication.login.LoginScreen
import `in`.rk585.notes.ui.authentication.navigation.authScreenModule
import `in`.rk585.notes.ui.design.theme.NotesTheme

suspend fun main() {

    ScreenRegistry {
        authScreenModule()
    }

    val component: ApplicationComponent by unsafeLazy {
        ApplicationComponent::class.create() as ApplicationComponent
    }
    val viewModels by unsafeLazy { component.viewModels }

    awaitApplication {
        Window(onCloseRequest = ::exitApplication, title = "Notes") {
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
