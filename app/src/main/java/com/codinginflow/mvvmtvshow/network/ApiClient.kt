package com.codinginflow.mvvmtvshow.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://www.episodate.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}