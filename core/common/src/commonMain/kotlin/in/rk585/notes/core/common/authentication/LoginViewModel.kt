package `in`.rk585.notes.core.common.authentication

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class LoginViewModel(
    private val client: SupabaseClient
) : ScreenModel {

    init {
        coroutineScope.launch {
            println(client.gotrue.currentSessionOrNull())
        }
    }

    fun login(email: String, password: String) {
        println("Trying ro login $email:$password")
        coroutineScope.launch {
            client.gotrue.loginWith(Email) {
                this.email = email
                this.password = password
            }
            println(client.gotrue.currentAccessTokenOrNull())
        }
    }
}
