package com.anahitavakoli.apps.filimo.model

import com.google.gson.annotations.SerializedName

data class HomeBanner (
    @SerializedName("ALL_IN_ONE_VIDEO")
    var banners: List<Banner>
        )