package kr.com.rlwhd.kotlinexample.dgmvp.di.module

import dagger.Module
import dagger.Provides
import kr.com.rlwhd.kotlinexample.dgmvp.di.GitHubApi
import kr.com.rlwhd.kotlinexample.dgmvp.di.annotation.UserScope
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    @UserScope
    fun provideGitHubApi(retrofit: Retrofit):GitHubApi = retrofit.create(GitHubApi::class.java)
}