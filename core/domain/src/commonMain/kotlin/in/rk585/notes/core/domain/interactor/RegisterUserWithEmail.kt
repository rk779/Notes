package `in`.rk585.notes.core.domain.interactor

import `in`.rk585.notes.core.data.repository.user.UserRepository
import `in`.rk585.notes.core.domain.Interactor
import me.tatarka.inject.annotations.Inject

@Inject
class RegisterUserWithEmail(
    private val userRepository: UserRepository
) : Interactor<RegisterUserWithEmail.Params>() {

    override suspend fun doWork(params: Params) {
        userRepository.registerWithEmail(
            Pair(params.email, params.password)
        )
    }

    data class Params(val email: String, val password: String)
}
