package `in`.rk585.notes.core.data.repository.auth

import me.tatarka.inject.annotations.Provides

interface AuthBinds {

    @Provides
    fun provideUserRepository(bind: AuthRepositoryImpl): AuthRepository = bind
}
