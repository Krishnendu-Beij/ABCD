package com.example.abcd.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abcd.R
import com.example.abcd.modelclasses.Article
import kotlinx.android.synthetic.main.item_view.view.*

class AppAdapter (val articles : List<Article>) : RecyclerView.Adapter<AppAdapter.NewsHolder>() {
    var onTap: ((Article) -> Unit)? = null

    inner class NewsHolder(ViewItem: View) : RecyclerView.ViewHolder(ViewItem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val appView = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return NewsHolder(appView)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.itemView.apply {
            Glide.with(this).load(articles[position].urlToImage).into(holder.itemView.imageViewNewsImage)
            textViewNewsTitle.text = articles[position].title
            textViewNewsDescription.text = articles[position].description
        }
        holder.itemView.setOnClickListener{
            Log.d("TRACK_ITEM_CLICK", it.toString())
            onTap?.invoke(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

}