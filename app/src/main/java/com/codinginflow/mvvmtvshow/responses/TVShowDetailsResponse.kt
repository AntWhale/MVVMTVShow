package com.codinginflow.mvvmtvshow.responses

import com.codinginflow.mvvmtvshow.models.TvShowDetails
import com.google.gson.annotations.SerializedName

data class TVShowDetailsResponse(
    @SerializedName("tvShow")
    val tvShowDetails: TvShowDetails
)