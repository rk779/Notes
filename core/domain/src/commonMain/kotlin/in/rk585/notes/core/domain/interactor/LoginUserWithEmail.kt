package `in`.rk585.notes.core.domain.interactor

import `in`.rk585.notes.core.data.repository.auth.AuthRepository
import `in`.rk585.notes.core.domain.Interactor
import me.tatarka.inject.annotations.Inject

@Inject
class LoginUserWithEmail(
    private val authRepository: AuthRepository
) : Interactor<LoginUserWithEmail.Params>() {

    override suspend fun doWork(params: Params) {
        authRepository.loginWithEmail(
            Pair(params.email, params.password)
        ).getOrThrow()
    }

    data class Params(val email: String, val password: String)
}
