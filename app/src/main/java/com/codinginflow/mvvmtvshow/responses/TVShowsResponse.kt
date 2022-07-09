package com.codinginflow.mvvmtvshow.responses


import com.codinginflow.mvvmtvshow.models.TVShow
import com.google.gson.annotations.SerializedName

data class TVShowsResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("tv_shows")
    val tvShows: List<TVShow>
)