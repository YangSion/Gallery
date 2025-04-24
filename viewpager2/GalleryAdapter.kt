package com.xxx.xxx.xxx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.smtlink.testdemokt.R

class GalleryAdapter(private val items: List<Info>) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].title
        Glide.with(holder.imageView.context)
            .load(items[position].imgUrl)
            .apply(RequestOptions().transform(RoundedCorners(20)))
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = items.size
}

data class Info(val title: String, val imgUrl: String)
