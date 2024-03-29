package `in`.rk585.notes.core.network.inject

import `in`.rk585.notes.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import me.tatarka.inject.annotations.Provides

interface NetworkComponent {

    @Provides
    fun provideClient(): SupabaseClient {
        return createSupabaseClient(BuildConfig.API_URL, BuildConfig.API_KEY) {
            install(GoTrue)
            install(Postgrest)
        }
    }

    @Provides
    fun provideGoTrue(client: SupabaseClient): GoTrue {
        return client.gotrue
    }

    @Provides
    fun providePostgrest(client: SupabaseClient): Postgrest {
        return client.postgrest
    }
}
