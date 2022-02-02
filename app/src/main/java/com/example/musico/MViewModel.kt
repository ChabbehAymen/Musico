package com.example.musico

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.example.musico.placeholder.AllSongsPlaceHolder
import com.example.musico.placeholder.HistoryItemPlaceHolder
import com.example.musico.placeholder.RandomPickerPlaceHolder
import java.io.File

class MViewModel : ViewModel() {
    private val _songs = arrayListOf<SongStateUi>()
    private val _randomSongs = arrayListOf<SongStateUi>()
    private val _songsHistory = arrayListOf<SongStateUi>()


    @SuppressLint("StaticFieldLeak")
    var context: Context? = null
    val songs get() = _songs
    val randomSongs get() = _randomSongs
    val songsHistory get() = _songsHistory


    fun main(){
        loadData()
        addRandomSongs()
    }

    @SuppressLint("Range")
    private fun loadData() {
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID
        )
        val cursor = context?.contentResolver?.query(uri, projection, selection,null, MediaStore.Audio.Media.DATE_ADDED,null)
        if (cursor != null)
            if (cursor.moveToFirst())
                do {
                    val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    val path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                    val uri  = Uri.parse("content://media/external/audio/albumart")
                    val artUri = Uri.withAppendedPath(uri, albumId.toString()).toString()
                    val song = SongStateUi(id = id, title = title, artist = artist, duration = duration, path = path, coverUri = artUri)
                    val file = File(song.path)
                    if (file.exists())
                        _songs.add(song)

                }while (cursor.moveToNext())
                cursor?.close()
    }

    private fun addRandomSongs(){
        _randomSongs.add(pickRandomSong())

    }

    private fun pickRandomSong(): SongStateUi{
        return songs.random()
    }

}
