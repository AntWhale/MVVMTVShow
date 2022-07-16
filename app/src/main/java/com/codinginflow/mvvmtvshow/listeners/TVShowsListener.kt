package com.codinginflow.mvvmtvshow.listeners

import com.codinginflow.mvvmtvshow.models.TVShow

interface TVShowsListener {
    fun onTVShowClicked(tvShow: TVShow)
}