package com.anahitavakoli.apps.filimo.api

import com.anahitavakoli.apps.filimo.model.HomeBanner
import com.anahitavakoli.apps.filimo.model.HomeVideoModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {

    @GET("api.php?home_banner")
    fun getHomeBanner():Call<HomeBanner>

    @GET("api.php?home_videos")
    fun getHomeVideo():Call<HomeVideoModel>

    @GET("api.php")
    fun search(@Query("search_text") text : String): Call<ResponseBody>
}