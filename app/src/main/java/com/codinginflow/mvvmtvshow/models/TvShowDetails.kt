package com.codinginflow.mvvmtvshow.models


import com.google.gson.annotations.SerializedName

data class TvShowDetails(
    @SerializedName("url")
    val url: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("runtime")
    val runtime: String,

    @SerializedName("image_path")
    val imagePath: String,

    @SerializedName("rating")
    val rating: String,

    @SerializedName("genres")
    val genres: List<String>,

    @SerializedName("pictures")
    val pictures: List<String>,

)