package com.anahitavakoli.apps.filimo

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.anahitavakoli.apps.filimo.adapter.BannerAdapter
import com.anahitavakoli.apps.filimo.adapter.CategoryAdapter
import com.anahitavakoli.apps.filimo.adapter.VideoAdapter
import com.anahitavakoli.apps.filimo.api.ApiCaller
import com.anahitavakoli.apps.filimo.api.IService
import com.anahitavakoli.apps.filimo.api.RetrofitClient
import com.anahitavakoli.apps.filimo.databinding.ActivityMainBinding
import com.anahitavakoli.apps.filimo.model.Banner
import com.anahitavakoli.apps.filimo.model.HomeBanner
import com.anahitavakoli.apps.filimo.model.HomeVideoModel
import com.anahitavakoli.apps.filimo.model.IResponse
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var apiCaller: ApiCaller = ApiCaller()

    // List View object
    lateinit var listView: ListView

    // Define array adapter for ListView
    lateinit var adapter: ArrayAdapter<String>

    // Define array List for List View data
    lateinit var mylist: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // initialise ListView with id
        listView = findViewById<ListView>(R.id.search_list)

        // Add items to Array List
        mylist = ArrayList()
        mylist.add("C")
        mylist.add("C++")
        mylist.add("C#")
        mylist.add("Java")
        mylist.add("Advanced java")
        mylist.add("Interview prep with c++")
        mylist.add("Interview prep with java")
        mylist.add("data structures with c")
        mylist.add("data structures with java")

        // Set adapter to ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mylist)
        listView.adapter = adapter


        apiCaller.getHomeBanner(object : IResponse<HomeBanner>{
            override fun onSuccess(response: HomeBanner) {
                binding.recyclerBanner.adapter = BannerAdapter(applicationContext,response.banners)
                binding.recyclerBanner.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
            }

            override fun onFailure(errorResponse: String) {
                TODO("Not yet implemented")
            }

        })

        apiCaller.getHomeVideos(object: IResponse<HomeVideoModel>{
            override fun onSuccess(response: HomeVideoModel) {

                binding.recyclerNewVideo.adapter = VideoAdapter(applicationContext, response.baseModel.featuredVideo)
                binding.recyclerNewVideo.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)

                binding.recyclerAllVideo.adapter = VideoAdapter(applicationContext, response.baseModel.allVideo)
                binding.recyclerAllVideo.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)

                binding.recyclerLatestVideo.adapter = VideoAdapter(applicationContext, response.baseModel.latestVideo)
                binding.recyclerLatestVideo.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)

            }

            override fun onFailure(errorResponse: String) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate menu with items using MenuInflator
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        // Initialise menu item search bar
        // with id and take its object
        val searchViewItem = menu.findItem(R.id.search_bar)
        val searchView = MenuItemCompat.getActionView(searchViewItem) as SearchView

        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Override onQueryTextSubmit method which is call when submit query is searched
            override fun onQueryTextSubmit(query: String): Boolean {
                // If the list contains the search query than filter the adapter
                // using the filter method with the query as its argument
                if (mylist.contains(query)) {
                    adapter.filter.filter(query)
                } else {
                    // Search query not found in List View
                    Toast.makeText(this@MainActivity, "Not found", Toast.LENGTH_LONG).show()
                }
                return false
            }

            // This method is overridden to filter the adapter according
            // to a search query when the user is typing search
            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}