package com.codinginflow.mvvmtvshow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codinginflow.mvvmtvshow.repositories.TVShowDetailsRepository
import com.codinginflow.mvvmtvshow.responses.TVShowDetailsResponse

class TVShowDetailsViewModel() : ViewModel() {
    val tvShowDetailsRepository: TVShowDetailsRepository = TVShowDetailsRepository()

    fun getTVShowDetails(tvShowId: Int) : LiveData<TVShowDetailsResponse> {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId)
    }
}