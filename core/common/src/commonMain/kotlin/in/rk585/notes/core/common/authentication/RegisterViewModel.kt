package `in`.rk585.notes.core.common.authentication

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import `in`.rk585.notes.core.data.repository.user.UserRepository
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class RegisterViewModel(private val userRepository: UserRepository) : ScreenModel {

    fun registerWithEmail(credentials: Pair<String, String>) {
        coroutineScope.launch {
            userRepository.registerWithEmail(credentials)
        }
    }
}
