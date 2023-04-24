package `in`.rk585.notes.core.data.repository.user

import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import me.tatarka.inject.annotations.Inject

@Inject
class UserRepositoryImpl(private val client: GoTrue) : UserRepository {

    override suspend fun loginWithEmail(credentials: Pair<String, String>) {
        client.loginWith(Email) {
            email = credentials.first
            password = credentials.second
        }
    }

    override suspend fun logout() {
        client.invalidateSession()
    }

    override suspend fun registerWithEmail(credentials: Pair<String, String>): Email.Result? {
        return client.signUpWith(Email) {
            email = credentials.first
            password = credentials.second
        }
    }
}
