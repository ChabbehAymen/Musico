package com.example.musico.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.musico.R
import com.example.musico.SongStateUi
import com.example.musico.databinding.SquareMusicardItemBinding

class HistoryAdapter (
    private val values: List<SongStateUi>, private val context: Context
        ): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            SquareMusicardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        if (item.title.length > 13) holder.itemTitle.text = minimizeSongTitle(item.title)
        else holder.itemTitle.text = item.title
        Glide.with(context)
            .load(item.coverUri)
            .apply(RequestOptions().placeholder(R.drawable.music_caver_img).centerCrop())
            .into(holder.itemCover)

    }

    private fun minimizeSongTitle(title: String): String {
        val tempTitle = title.toCharArray(0, 11)
        return "${String(tempTitle)}.."
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: SquareMusicardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemTitle: TextView = binding.horizontalSongTitle
        val itemCover: ImageView = binding.horizontalCoverImage

    }
}