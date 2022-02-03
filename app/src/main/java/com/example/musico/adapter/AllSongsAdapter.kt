package com.example.musico.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.musico.R
import com.example.musico.SongStateUi
import com.example.musico.databinding.VerticalMusicaverItemBinding
import com.example.musico.formatDuration

class AllSongsAdapter(
    private val values: List<SongStateUi>, private val context: Context
) : RecyclerView.Adapter<AllSongsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            VerticalMusicaverItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        if (item.title.length > 30) holder.itemTitle.text = minimizeSongTitle(item.title)
        else holder.itemTitle.text = item.title
        holder.itemArtists.text = item.artist
        holder.itemDuration.text = formatDuration(item.duration)
        Glide.with(context)
            .load(item.coverUri)
            .apply(RequestOptions().placeholder(R.drawable.music_caver_img).centerCrop())
            .into(holder.itemCover)
        holder.playBtn.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                return@setOnTouchListener true
            }
            Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
            return@setOnTouchListener true
        }


    }

    private fun minimizeSongTitle(title: String): String {
        val tempTitle = title.toCharArray(0, 28)
        return "${String(tempTitle)}.."
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: VerticalMusicaverItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemTitle: TextView = binding.verticalSongTitle
        val itemArtists: TextView = binding.songArtiste
        val itemDuration: TextView = binding.songDuration
        val itemCover: ImageView = binding.verticalCoverImage
        val playBtn = binding.rcPlayBtn

    }
}