package kr.com.rlwhd.kotlinexample.dgmvp.di.component

import dagger.Component
import kr.com.rlwhd.kotlinexample.dgmvp.di.annotation.UserScope
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.ApiModule
import kr.com.rlwhd.kotlinexample.dgmvp.di.presenter.MainPresenter

@UserScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ApiModule::class]
)
interface PresenterComponent {
    fun inject(presenter: MainPresenter)
//    fun inject(presenter: NavigationPresenter)
}