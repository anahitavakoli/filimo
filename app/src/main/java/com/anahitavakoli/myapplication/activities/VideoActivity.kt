package com.anahitavakoli.myapplication.activities

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.anahitavakoli.myapplication.databinding.ActivityVideoBinding
import com.anahitavakoli.myapplication.model.Video
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem


class VideoActivity : AppCompatActivity() {

    lateinit var binding: ActivityVideoBinding
    lateinit var player : ExoPlayer
    lateinit var bundle: Bundle
    var video : Video?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        bundle = intent.extras!!

        video = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("videoObj",Video::class.java)
        } else{
            intent.getParcelableExtra<Video>("videoObj")
        }

        binding.txtTitle.setText(video!!.videoTitle)

        binding.txtDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(video!!.videoDescription, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(video!!.videoDescription)
        }

        player = ExoPlayer.Builder(applicationContext).build()
        binding.videoView.player = player

        val item = MediaItem.fromUri(Uri.parse(video!!.videoUrl))
        player.setMediaItem(item)
        player.prepare()
        player.play()
    }

    override fun onStop() {
        super.onStop()
        if(player!=null){
            player.stop()
        }
    }
}