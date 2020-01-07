package kr.com.rlwhd.kotlinexample.dgmvp.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.dgmvp.di.annotation.UserScope

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @UserScope
    fun provideActivity() = activity

}