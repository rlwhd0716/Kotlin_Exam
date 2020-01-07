package kr.com.rlwhd.kotlinexample.dgmvp.di.component

import dagger.Component
import kr.com.rlwhd.kotlinexample.MainActivity
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.ActivityModule
import kr.com.rlwhd.kotlinexample.dgmvp.di.annotation.UserScope

@UserScope
@Component(
    dependencies = arrayOf(AppComponent::class),
    modules = arrayOf(ActivityModule::class)
)
interface ActivityComponent {
    fun inject(context: MainActivity): Unit
}