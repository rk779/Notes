package `in`.rk585.notes.android.inject

import android.app.Application
import android.content.Context
import `in`.rk585.notes.android.NotesApplication
import `in`.rk585.notes.core.base.inject.ApplicationScope
import `in`.rk585.notes.core.common.ViewModelComponent
import `in`.rk585.notes.core.network.inject.NetworkComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@ApplicationScope
@Component
abstract class ApplicationComponent(
    @get:Provides val application: Application
) : NetworkComponent, ViewModelComponent {

    val bind: ViewModelComponent
        @Provides get() = this

    companion object {
        fun from(context: Context): ApplicationComponent {
            return (context.applicationContext as NotesApplication).component
        }
    }
}
