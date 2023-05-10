package `in`.rk585.notes.core.domain.interactor

import `in`.rk585.notes.core.data.repository.auth.AuthRepository
import `in`.rk585.notes.core.domain.Interactor
import me.tatarka.inject.annotations.Inject

@Inject
class RegisterUserWithEmail(
    private val authRepository: AuthRepository
) : Interactor<RegisterUserWithEmail.Params>() {

    override suspend fun doWork(params: Params) {
        authRepository.registerWithEmail(
            Pair(params.email, params.password)
        ).getOrThrow()
    }

    data class Params(val email: String, val password: String)
}
