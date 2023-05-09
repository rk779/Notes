package `in`.rk585.notes.core.data.repository.user

import me.tatarka.inject.annotations.Provides

interface UserBinds {

    @Provides
    fun provideUserRepository(bind: UserRepositoryImpl): UserRepository = bind
}
