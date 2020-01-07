package kr.com.rlwhd.kotlinexample.dgmvp.di.component

import dagger.Component
import kr.com.rlwhd.kotlinexample.dgmvp.SettingPreference
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.AppModule
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.NetworkModule
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.PreferenceModule
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    PreferenceModule::class,
    NetworkModule::class
))

interface AppComponent {
    fun retrofit(): Retrofit
    fun setting(): SettingPreference
}