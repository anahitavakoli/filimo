package com.anahitavakoli.apps.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    @SerializedName("id")
    var id: String,
    @SerializedName("cat_id")
    var catId : String,
    @SerializedName("video_type")
    var videoType: String,
    @SerializedName("video_title")
    var videoTitle: String,
    @SerializedName("video_url")
    var videoUrl: String,
    @SerializedName("video_thumbnail_b")
    var videoThumbnailB: String,
    @SerializedName("video_thumbnail_s")
    var videoThumbnailS: String,
    @SerializedName("video_duration")
    var videoDuration: String,
    @SerializedName("video_description")
    var videoDescription: String,
    @SerializedName("rate_avg")
    var rateAvg: String,
    @SerializedName("totel_viewer")
    var totelViewer: String,
    @SerializedName("cid")
    var cId: String,
    @SerializedName("category_name")
    var categoryName: String,
    @SerializedName("category_image")
    var categoryImage: String,
    @SerializedName("category_image_thumb")
    var categoryImageThumb: String
): Parcelable
