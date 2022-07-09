package com.codinginflow.mvvmtvshow.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codinginflow.mvvmtvshow.network.ApiClient
import com.codinginflow.mvvmtvshow.network.ApiService
import com.codinginflow.mvvmtvshow.responses.TVShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopularTVShowsRepository {

    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

    fun getMostPopularTVShows(page: Int): LiveData<TVShowsResponse> {
        val data = MutableLiveData<TVShowsResponse>()
        apiService.getMostPopularTVShows(page).enqueue(object: Callback<TVShowsResponse> {
            override fun onResponse(
                call: Call<TVShowsResponse>,
                response: Response<TVShowsResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                data.value = null
            }

        })
        return data
    }
}