package `in`.rk585.notes.core.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.compositionLocalOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import `in`.rk585.notes.core.common.authentication.LoginViewModel
import `in`.rk585.notes.core.common.authentication.RegisterViewModel

interface ViewModelComponent {
    val loginViewModel: () -> LoginViewModel
    val registerViewModel: () -> RegisterViewModel
    val splashViewModel: () -> SplashViewModel
}

val LocalViewModel = compositionLocalOf<ViewModelComponent> {
    error("ViewModelComponent not found")
}

@Composable
inline fun <reified VM : ScreenModel> Screen.viewModel(
    tag: String? = null,
    crossinline factory: @DisallowComposableCalls ViewModelComponent.() -> VM,
): VM {
    val viewModelFactory = LocalViewModel.current
    return rememberScreenModel(tag) { viewModelFactory.factory() }
}
