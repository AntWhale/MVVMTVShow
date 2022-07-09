package com.codinginflow.mvvmtvshow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codinginflow.mvvmtvshow.repositories.MostPopularTVShowsRepository
import com.codinginflow.mvvmtvshow.responses.TVShowsResponse

class MostPopularTVShowsViewModel: ViewModel() {
    val mostPopularTVShowsRepository = MostPopularTVShowsRepository()

    fun getMostPopularTVShows(page: Int): LiveData<TVShowsResponse> = mostPopularTVShowsRepository.getMostPopularTVShows(page)
}