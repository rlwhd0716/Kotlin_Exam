package kr.com.rlwhd.kotlinexample.dagger.module

import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.dagger.model.Dog

@Module
object DogModule {

    @Provides
    fun provideDog() : Dog {
        return Dog()
    }
}