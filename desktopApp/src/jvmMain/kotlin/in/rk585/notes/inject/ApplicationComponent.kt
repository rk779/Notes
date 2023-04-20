package `in`.rk585.notes.inject

import `in`.rk585.notes.core.base.inject.ApplicationScope
import `in`.rk585.notes.core.common.ViewModelComponent
import `in`.rk585.notes.core.network.inject.NetworkComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@ApplicationScope
@Component
abstract class ApplicationComponent : NetworkComponent, ViewModelComponent {
    val viewModels: ViewModelComponent
        @Provides get() = this
}
