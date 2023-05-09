package `in`.rk585.notes.core.domain.interactor

import `in`.rk585.notes.core.data.repository.user.UserRepository
import `in`.rk585.notes.core.domain.Interactor
import me.tatarka.inject.annotations.Inject

@Inject
class UserEmailLogin(
    private val userRepository: UserRepository
) : Interactor<UserEmailLogin.Params>() {

    override suspend fun doWork(params: Params) {
        userRepository.loginWithEmail(
            Pair(params.email, params.password)
        )
    }

    data class Params(val email: String, val password: String)
}
