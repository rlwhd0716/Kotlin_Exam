package kr.com.rlwhd.kotlinexample.dgmvp.di

import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Leopold
 */
interface GitHubApi {

    @GET("/search/repositories")
    fun searchRepositories(@QueryMap params: Map<String, String>)

}