package `in`.rk585.notes.core.base.utils

import kotlinx.atomicfu.atomic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class ObservableLoadingCounter {
    private val count = atomic(0)
    private val loadingState = MutableStateFlow(count.value)

    val observable: Flow<Boolean>
        get() = loadingState.map { it > 0 }.distinctUntilChanged()

    fun addLoader() {
        loadingState.update { count.incrementAndGet() }
    }

    fun removeLoader() {
        loadingState.update { count.decrementAndGet() }
    }
}
