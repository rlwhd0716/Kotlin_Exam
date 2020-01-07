package kr.com.rlwhd.kotlinexample.dgmvp.dagger.module

import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.dgmvp.dagger.model.Dog

@Module
object DogModule {

    @Provides
    fun provideDog() : Dog {
        return Dog()
    }
}