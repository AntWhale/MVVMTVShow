package com.codinginflow.mvvmtvshow.models


import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("season")
    val season: String,

    @SerializedName("edpisode")
    val episode: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("air_date")
    val airDate: String
)