package com.bri.androidstudy.data

import com.bri.androidstudy.domain.model.UserResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MainDataSource {
    @GET("/search/users")
    suspend fun getList(
        @Query("page") page: Int,
        @Query("q") query: String,
        @Query("per_page") perPage: Int
    ): UserResult
}
