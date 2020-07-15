package com.wahidhidayat.themoviedb.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.model.VideoResult
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter(
    private val listVideos: ArrayList<VideoResult>,
    private val context: Context?
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: VideoResult) {
            with(itemView) {
                tv_title_video.text = video.name
                tv_site.text = video.site
                tv_type.text = video.type
                Glide.with(context)
                    .load("https://img.youtube.com/vi/" + video.key + "/hqdefault.jpg")
                    .into(iv_video)
                iv_video.setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + video.key)
                    )
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listVideos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video: VideoResult = listVideos[position]
        holder.bind(video)
    }
}