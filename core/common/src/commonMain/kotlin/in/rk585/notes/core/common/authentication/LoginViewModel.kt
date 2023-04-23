package `in`.rk585.notes.core.common.authentication

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import `in`.rk585.notes.core.base.collectStatus
import `in`.rk585.notes.core.base.utils.ObservableLoadingCounter
import `in`.rk585.notes.core.base.utils.UiMessage
import `in`.rk585.notes.core.base.utils.UiMessageManager
import `in`.rk585.notes.core.domain.interactor.UserEmailLogin
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class LoginViewModel(
    private val userEmailLogin: UserEmailLogin
) : ScreenModel {

    private val loadingState = ObservableLoadingCounter()
    private val uiMessageManager = UiMessageManager()

    val state: StateFlow<LoginViewState> = combine(
        loadingState.observable,
        uiMessageManager.message,
        ::LoginViewState
    ).stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LoginViewState.Empty
    )

    fun loginWithEmail(email: String, password: String) {
        coroutineScope.launch {
            userEmailLogin.invoke(UserEmailLogin.Params(email, password))
                .collectStatus(loadingState, uiMessageManager)
        }
    }

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessageManager.clearMessage(id)
        }
    }
}

@Immutable
data class LoginViewState(
    val loading: Boolean = false,
    val uiMessage: UiMessage? = null
) {
    companion object {
        val Empty = LoginViewState()
    }
}
