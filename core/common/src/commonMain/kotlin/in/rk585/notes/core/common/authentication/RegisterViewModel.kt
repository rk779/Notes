package `in`.rk585.notes.core.common.authentication

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import `in`.rk585.notes.core.base.collectStatus
import `in`.rk585.notes.core.base.utils.ObservableLoadingCounter
import `in`.rk585.notes.core.base.utils.UiMessage
import `in`.rk585.notes.core.base.utils.UiMessageManager
import `in`.rk585.notes.core.domain.interactor.RegisterUserWithEmail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class RegisterViewModel(
    private val registerUserWithEmail: RegisterUserWithEmail
) : ScreenModel {

    private val loadingState = ObservableLoadingCounter()
    private val uiMessageManager = UiMessageManager()

    val state: StateFlow<RegisterViewState> = combine(
        loadingState.observable,
        uiMessageManager.message,
        ::RegisterViewState
    ).stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RegisterViewState.Empty
    )

    fun registerWithEmail(email: String, password: String) {
        coroutineScope.launch {
            registerUserWithEmail.invoke(
                RegisterUserWithEmail.Params(
                    email = email,
                    password = password
                )
            ).collectStatus(loadingState, uiMessageManager)
        }
    }

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessageManager.clearMessage(id)
        }
    }
}

@Immutable
data class RegisterViewState(
    val loading: Boolean = false,
    val uiMessage: UiMessage? = null
) {
    companion object {
        val Empty = RegisterViewState()
    }
}
