package com.codinginflow.mvvmtvshow.network

import com.codinginflow.mvvmtvshow.responses.TVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("most-popular")
    fun getMostPopularTVShows(@Query("page") page:Int): Call<TVShowsResponse>
}