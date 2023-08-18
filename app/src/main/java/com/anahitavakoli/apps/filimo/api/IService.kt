package com.anahitavakoli.apps.filimo.api

import com.anahitavakoli.apps.filimo.model.HomeVideoModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface IService {

    @GET("api.php?home_banner")
    fun getHomeBanner():Call<ResponseBody>

    @GET("api.php?home_videos")
    fun getHomeVideo():Call<HomeVideoModel>
}