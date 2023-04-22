package `in`.rk585.notes.ui.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class AuthScreen : ScreenProvider {
    object Login : AuthScreen()
    object Register : AuthScreen()
}
