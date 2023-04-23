package `in`.rk585.notes.core.common

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.flow.StateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class SplashViewModel(
    private val client: SupabaseClient
) : ScreenModel {
    val sessionStatus: StateFlow<SessionStatus>
        get() = client.gotrue.sessionStatus
}
