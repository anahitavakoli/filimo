package com.anahitavakoli.apps.filimo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://mobilemasters.ir/apps/filimo-android/").build()
}