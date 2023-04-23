package `in`.rk585.notes.core.data.repository.user

import `in`.rk585.notes.core.data.model.LoginWithEmail
import `in`.rk585.notes.core.data.model.RegisterWithEmail
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import me.tatarka.inject.annotations.Inject

@Inject
class UserRepositoryImpl(private val client: GoTrue) : UserRepository {

    override suspend fun loginWithEmail(data: LoginWithEmail) {
        client.loginWith(Email) {
            email = data.email
            password = data.password
        }
    }

    override suspend fun logout() {
        client.invalidateSession()
    }

    override suspend fun registerWithEmail(data: RegisterWithEmail): Email.Result? {
        return client.signUpWith(Email) {
            email = data.email
            password = data.password
        }
    }
}
