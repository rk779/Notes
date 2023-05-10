package `in`.rk585.notes.core.data.repository.auth

import io.github.jan.supabase.gotrue.providers.builtin.Email

interface AuthRepository {
    suspend fun loginWithEmail(credentials: Pair<String, String>): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun registerWithEmail(credentials: Pair<String, String>): Result<Email.Result?>
}
