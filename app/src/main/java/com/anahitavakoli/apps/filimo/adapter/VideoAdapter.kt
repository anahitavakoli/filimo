package com.anahitavakoli.apps.filimo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anahitavakoli.apps.filimo.R
import com.anahitavakoli.apps.filimo.activities.VideoActivity
import com.anahitavakoli.apps.filimo.model.Video
import com.squareup.picasso.Picasso

class VideoAdapter(mcontext: Context, data: List<Video>) : RecyclerView.Adapter<VideoAdapter.VideoVH>() {

    var context = mcontext
    var videoList = data
    var inflater = LayoutInflater.from(context)


    class VideoVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgVideo = itemView.findViewById<AppCompatImageView>(R.id.img_video)
        var txtTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_video_name)
        var cardView = itemView.findViewById<CardView>(R.id.card_video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVH {
        var view = inflater.inflate(R.layout.video_home_raw,null)
        return VideoVH(view)
    }

    override fun onBindViewHolder(holder: VideoVH, position: Int) {
        val video: Video = videoList.get(position)
        holder.txtTitle.setText(video.videoTitle)
        Picasso.get().load(video.videoThumbnailB).into(holder.imgVideo)
        holder.cardView.setOnClickListener {
            val intent = Intent(context, VideoActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("id",111)
            intent.putExtra("videoObj", video)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}