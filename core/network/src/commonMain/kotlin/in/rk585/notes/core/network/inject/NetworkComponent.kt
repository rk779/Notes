package `in`.rk585.notes.core.network.inject

import `in`.rk585.notes.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import me.tatarka.inject.annotations.Provides

interface NetworkComponent {

    @Provides
    fun provideClient(): SupabaseClient {
        return createSupabaseClient(BuildConfig.API_URL, BuildConfig.API_KEY) {
            install(GoTrue)
        }
    }
}
