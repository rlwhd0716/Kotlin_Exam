package kr.com.rlwhd.kotlinexample.dgmvp.di.module

import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.ApplicationKt
import kr.com.rlwhd.kotlinexample.dgmvp.SettingPreference
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    @Singleton
    fun provideSettingPreference(mApplicationKt: ApplicationKt): SettingPreference {
        return SettingPreference(mApplicationKt)
    }
}