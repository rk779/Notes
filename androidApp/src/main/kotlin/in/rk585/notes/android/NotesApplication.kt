package `in`.rk585.notes.android

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import `in`.rk585.notes.ui.authentication.navigation.authScreenModule

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            authScreenModule()
        }
    }
}