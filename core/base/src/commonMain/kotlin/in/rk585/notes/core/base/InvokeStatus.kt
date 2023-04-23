package `in`.rk585.notes.core.base

import `in`.rk585.notes.core.base.utils.ObservableLoadingCounter
import `in`.rk585.notes.core.base.utils.UiMessage
import `in`.rk585.notes.core.base.utils.UiMessageManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

sealed class InvokeStatus
object InvokeStarted : InvokeStatus()
object InvokeSuccess : InvokeStatus()
data class InvokeError(val throwable: Throwable) : InvokeStatus()

suspend inline fun Flow<InvokeStatus>.onEachStatus(
    counter: ObservableLoadingCounter,
    uiMessageManager: UiMessageManager? = null,
    noinline onSuccess: (() -> Unit)? = null
): Flow<InvokeStatus> = onEach { status ->
    when (status) {
        InvokeStarted -> counter.addLoader()
        InvokeSuccess -> {
            counter.removeLoader()
            onSuccess?.invoke()
        }

        is InvokeError -> {
            uiMessageManager?.emitMessage(UiMessage(status.throwable))
            counter.removeLoader()
        }
    }
}

suspend inline fun Flow<InvokeStatus>.collectStatus(
    counter: ObservableLoadingCounter,
    uiMessageManager: UiMessageManager? = null,
    noinline onSuccess: (() -> Unit)? = null
): Unit = onEachStatus(counter, uiMessageManager, onSuccess).collect()
