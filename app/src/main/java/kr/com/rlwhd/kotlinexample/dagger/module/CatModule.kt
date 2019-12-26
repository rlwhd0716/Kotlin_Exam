package kr.com.rlwhd.kotlinexample.dagger.module

import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.dagger.model.Cat

@Module
object CatModule {

    @Provides
    fun provideCat(): Cat {
        return Cat()
    }
}