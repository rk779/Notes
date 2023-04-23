package `in`.rk585.notes.core.data.repository.user

import `in`.rk585.notes.core.data.model.LoginWithEmail
import `in`.rk585.notes.core.data.model.RegisterWithEmail
import io.github.jan.supabase.gotrue.providers.builtin.Email

interface UserRepository {
    suspend fun loginWithEmail(data: LoginWithEmail)
    suspend fun logout()
    suspend fun registerWithEmail(data: RegisterWithEmail): Email.Result?
}
