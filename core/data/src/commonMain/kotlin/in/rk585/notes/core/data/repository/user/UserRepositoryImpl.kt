package `in`.rk585.notes.core.data.repository.user

import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.JsonObject
import me.tatarka.inject.annotations.Inject

@Inject
class UserRepositoryImpl(
    private val client: GoTrue,
    private val postgrest: Postgrest
) : UserRepository {

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
        /**
         * Check if we have an existing user with the same email address.
         * throw an exception if an account with the same email already exists.
         */
        val result = postgrest["profiles"].select(
            columns = Columns.list("email")
        ) {
            eq("email", credentials.first)
        }.decodeSingleOrNull<JsonObject>()

        if (result != null) {
            throw Exception("User with the same email already exists.")
        }

        return client.signUpWith(Email) {
            email = credentials.first
            password = credentials.second
        }
    }
}
