package kr.com.rlwhd.kotlinexample.dagger

import dagger.Component
import kr.com.rlwhd.kotlinexample.dagger.module.CatModule
import kr.com.rlwhd.kotlinexample.dagger.module.DogModule
import javax.inject.Singleton

@Singleton
@Component (modules = [DogModule::class, CatModule::class])
interface PetComponent {
    fun inject(daggerActivity: DaggerActivity)

    @Component.Builder
    interface Builder {
        fun build() : PetComponent

        fun dogModule(dogModule : DogModule) : Builder
        fun catModule(catModule : CatModule) : Builder
    }
}