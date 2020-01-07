package kr.com.rlwhd.kotlinexample.dgmvp.di.presenter

import android.app.Activity
import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.dgmvp.di.annotation.UserScope

@Module
class ActivityPresenterModule {

    @Provides
    @UserScope
    fun provideMainPresenter(activity: Activity) = MainPresenter(activity)
}