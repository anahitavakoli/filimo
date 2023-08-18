package com.anahitavakoli.myapplication.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("featured_video")
    var featuredVideo: List<Video>,
    @SerializedName("latest_video")
    var latestVideo: List<Video>,
    @SerializedName("all_video")
    var allVideo: List<Video>,
    @SerializedName("category")
    var category: List<Category>
)
