package com.anahitavakoli.apps.filimo.api

import com.anahitavakoli.apps.filimo.model.HomeBanner
import com.anahitavakoli.apps.filimo.model.HomeVideoModel
import com.anahitavakoli.apps.filimo.model.IResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCaller {
    var iService : IService = RetrofitClient.retrofit.create(IService::class.java)

    fun getHomeBanner(listener: IResponse<HomeBanner>){
        iService.getHomeBanner().enqueue(object : Callback<HomeBanner>{
            override fun onResponse(call: Call<HomeBanner>, response: Response<HomeBanner>) {
                response.body()?.let { listener.onSuccess(it) }
            }

            override fun onFailure(call: Call<HomeBanner>, t: Throwable) {
                listener.onFailure(t.toString())
            }

        })
    }

    fun getHomeVideos(listener: IResponse<HomeVideoModel>){
        iService.getHomeVideo().enqueue(object : Callback<HomeVideoModel>{
            override fun onResponse(
                call: Call<HomeVideoModel>,
                response: Response<HomeVideoModel>
            ) {
                response.body()?.let { listener.onSuccess(it) }
            }

            override fun onFailure(call: Call<HomeVideoModel>, t: Throwable) {
                listener.onFailure(t.toString())
            }

        })
    }
}