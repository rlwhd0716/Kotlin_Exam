package kr.com.rlwhd.kotlinexample.dgmvp.di.module

import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.ApplicationKt
import javax.inject.Singleton

@Module
class AppModule(private val mApplicationKt: ApplicationKt) {

    @Provides
    @Singleton
    fun provideApp() = mApplicationKt
}