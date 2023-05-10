package `in`.rk585.notes.core.data.repository.auth

import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlin.runCatching
import kotlinx.serialization.json.JsonObject
import me.tatarka.inject.annotations.Inject

@Inject
class AuthRepositoryImpl(
    private val client: GoTrue,
    private val postgrest: Postgrest
) : AuthRepository {

    override suspend fun loginWithEmail(credentials: Pair<String, String>): Result<Unit> {
        return runCatching {
            client.loginWith(Email) {
                email = credentials.first
                password = credentials.second
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return runCatching {
            client.logout()
        }
    }

    override suspend fun registerWithEmail(
        credentials: Pair<String, String>
    ): Result<Email.Result?> {
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

        return runCatching {
            client.signUpWith(Email) {
                email = credentials.first
                password = credentials.second
            }
        }
    }
}
