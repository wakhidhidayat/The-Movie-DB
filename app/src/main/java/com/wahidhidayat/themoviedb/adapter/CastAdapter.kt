package com.wahidhidayat.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.model.Cast
import kotlinx.android.synthetic.main.item_credit.view.*

class CastAdapter(
    private val listCredit: ArrayList<Cast>,
    private val context: Context?
) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cast: Cast) {
            with(itemView) {
                tv_credit_name.text = cast.name
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL + "w500" + cast.profile_path)
                    .into(iv_credit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_credit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCredit.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast: Cast = listCredit[position]
        holder.bind(cast)
    }

}