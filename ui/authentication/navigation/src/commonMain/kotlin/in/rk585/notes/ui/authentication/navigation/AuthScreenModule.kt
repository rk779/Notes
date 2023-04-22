package `in`.rk585.notes.ui.authentication.navigation

import cafe.adriel.voyager.core.registry.screenModule
import `in`.rk585.notes.ui.authentication.login.LoginScreen
import `in`.rk585.notes.ui.authentication.signup.RegisterScreen
import `in`.rk585.notes.ui.navigation.AuthScreen

val authScreenModule = screenModule {
    register<AuthScreen.Login> {
        LoginScreen
    }
    register<AuthScreen.Register> {
        RegisterScreen
    }
}
