package `in`.rk585.notes.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import `in`.rk585.notes.core.common.SplashViewModel
import `in`.rk585.notes.core.common.viewModel
import `in`.rk585.notes.ui.navigation.AuthScreen
import io.github.jan.supabase.gotrue.SessionStatus

object SplashScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = viewModel { splashViewModel() }

        SplashScreen(
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    val loginScreen = rememberScreen(AuthScreen.Login)

    val snackBarHostState = remember { SnackbarHostState() }
    var sessionLoading by remember { mutableStateOf(false) }
    val sessionStatus by viewModel.sessionStatus.collectAsState()

    LaunchedEffect(sessionStatus) {
        when (sessionStatus) {
            is SessionStatus.Authenticated -> {
                snackBarHostState.showSnackbar("User is authenticated")
            }

            SessionStatus.LoadingFromStorage -> sessionLoading = true
            SessionStatus.NetworkError -> {
                snackBarHostState.showSnackbar("Network error")
            }

            SessionStatus.NotAuthenticated -> navigator.replace(loginScreen)
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(sessionLoading) {
                CircularProgressIndicator()
            }
        }
    }
}
