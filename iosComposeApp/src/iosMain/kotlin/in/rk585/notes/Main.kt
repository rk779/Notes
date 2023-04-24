package `in`.rk585.notes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import `in`.rk585.notes.core.base.extensions.unsafeLazy
import `in`.rk585.notes.core.common.LocalViewModel
import `in`.rk585.notes.inject.ApplicationComponent
import `in`.rk585.notes.inject.create
import `in`.rk585.notes.ui.authentication.navigation.authScreenModule
import `in`.rk585.notes.ui.design.theme.NotesTheme
import `in`.rk585.notes.ui.splash.SplashScreen
import platform.UIKit.UIViewController

@OptIn(ExperimentalAnimationApi::class)
fun MainViewController(): UIViewController {

    ScreenRegistry {
        authScreenModule()
    }

    val component: ApplicationComponent by unsafeLazy {
        ApplicationComponent::class.create() as ApplicationComponent
    }
    val viewModels by unsafeLazy { component.viewModels }

    return ComposeUIViewController {
        CompositionLocalProvider(LocalViewModel provides viewModels) {
            NotesTheme {
                Navigator(SplashScreen) {
                    SlideTransition(it)
                }
            }
        }
    }
}
