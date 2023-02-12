package com.anonymous.searchquery.data.api

import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MetMuseumService {
    @GET("search")
    suspend fun searchQuery(@Query("q") query: String): ApiResponse<SearchNetwork>
}
