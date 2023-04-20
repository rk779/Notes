package `in`.rk585.notes.core.network.inject

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import me.tatarka.inject.annotations.Provides

interface NetworkComponent {

    @Provides
    fun provideClient(): SupabaseClient {
        return createSupabaseClient("", "") {
            install(GoTrue)
        }
    }
}
