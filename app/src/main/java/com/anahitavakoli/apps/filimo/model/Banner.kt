package com.anahitavakoli.apps.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(
    @SerializedName("id")
    var id: String,
    @SerializedName("banner_name")
    var bannerName: String,
    @SerializedName("banner_image")
    var bannerImage: String,
    @SerializedName("banner_image_thumb")
    var bannerImageThumb: String,
    @SerializedName("banner_url")
    var bannerUrl: String
    ): Parcelable
