package `in`.rk585.notes.android

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import `in`.rk585.notes.android.inject.ApplicationComponent
import `in`.rk585.notes.android.inject.create
import `in`.rk585.notes.core.base.extensions.unsafeLazy
import `in`.rk585.notes.ui.authentication.navigation.authScreenModule

class NotesApplication : Application() {
    val component: ApplicationComponent by unsafeLazy { ApplicationComponent::class.create(this) }

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            authScreenModule()
        }
    }
}
