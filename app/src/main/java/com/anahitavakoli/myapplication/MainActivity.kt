package com.anahitavakoli.myapplication

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anahitavakoli.myapplication.adapter.BannerAdapter
import com.anahitavakoli.myapplication.adapter.CategoryAdapter
import com.anahitavakoli.myapplication.adapter.VideoAdapter
import com.anahitavakoli.myapplication.api.IService
import com.anahitavakoli.myapplication.api.RetrofitClient
import com.anahitavakoli.myapplication.databinding.ActivityMainBinding
import com.anahitavakoli.myapplication.model.Banner
import com.anahitavakoli.myapplication.model.HomeVideoModel
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val iService : IService = RetrofitClient.retrofit.create(IService::class.java)

        iService.getHomeBanner().enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val jsonObjectBanner = JSONObject(response.body()!!.string())
                val dataBanner = jsonObjectBanner.getString("ALL_IN_ONE_VIDEO")
                val jsonArrayBanner = JSONArray(dataBanner)

                val bannerList = mutableListOf<Banner>()
                for(i in 0 until jsonArrayBanner.length()){

                    val jObject: JSONObject = jsonArrayBanner.getJSONObject(i)
                    val id = jObject.getString("id")
                    val bannerName = jObject.getString("banner_name")
                    val bannerImage = jObject.getString("banner_image")
                    val bannerImageThumb = jObject.getString("banner_image_thumb")
                    val bannerUrl = jObject.getString("banner_url")

                    val banner = Banner(id,bannerName,bannerImage,bannerImageThumb,bannerUrl)
                    bannerList.add(banner)

                    binding.recyclerBanner.adapter = BannerAdapter(applicationContext,bannerList)
                    binding.recyclerBanner.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Failed","Failed")
            }

        })

        iService.getHomeVideo().enqueue(object:Callback<HomeVideoModel>{
            override fun onResponse(
                call: Call<HomeVideoModel>,
                response: Response<HomeVideoModel>
            ) {
                binding.recyclerNewVideo.adapter = response.body()?.baseModel?.let {
                    VideoAdapter(applicationContext,
                        it.featuredVideo)
                }
                binding.recyclerNewVideo.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)

                binding.recyclerLatestVideo.adapter = response.body()?.baseModel?.let {
                    VideoAdapter(applicationContext,
                        it.latestVideo)
                }
                binding.recyclerLatestVideo.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)

                binding.recyclerAllVideo.adapter = response.body()?.baseModel?.let {
                    VideoAdapter(applicationContext,
                        it.allVideo)
                }
                binding.recyclerAllVideo.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)

                binding.recyclerCategory.adapter = response.body()?.baseModel?.let {
                    CategoryAdapter(applicationContext,
                        it.category)
                }
                binding.recyclerCategory.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)


            }

            override fun onFailure(call: Call<HomeVideoModel>, t: Throwable) {
                Log.e("","")
            }

        })

    }
}