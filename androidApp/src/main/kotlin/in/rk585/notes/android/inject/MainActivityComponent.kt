package `in`.rk585.notes.android.inject

import android.app.Activity
import `in`.rk585.notes.core.base.inject.ActivityScope
import `in`.rk585.notes.core.common.ViewModelComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@ActivityScope
@Component
abstract class MainActivityComponent(
    @get:Provides val activity: Activity,
    @Component val applicationComponent: ApplicationComponent = ApplicationComponent.from(activity),
) {
    abstract val viewModels: ViewModelComponent
}
