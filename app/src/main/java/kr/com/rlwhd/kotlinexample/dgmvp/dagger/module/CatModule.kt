package kr.com.rlwhd.kotlinexample.dgmvp.dagger.module

import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.dgmvp.dagger.model.Cat

@Module
object CatModule {

    @Provides
    fun provideCat(): Cat {
        return Cat()
    }
}