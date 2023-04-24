package `in`.rk585.notes.core.data.repository.user

import io.github.jan.supabase.gotrue.providers.builtin.Email

interface UserRepository {
    suspend fun loginWithEmail(credentials: Pair<String, String>)
    suspend fun logout()
    suspend fun registerWithEmail(credentials: Pair<String, String>): Email.Result?
}
