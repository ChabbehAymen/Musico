package com.example.musico

import java.util.concurrent.TimeUnit

data class SongStateUi(val id: String,
                       val title: String,
                       val coverUri: String,
                       val artist: String,
                       val duration: Long = 0,
                       val path: String)

fun formatDuration(duration: Long): String{
    val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
    val seconds = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)) -
            minutes*TimeUnit.SECONDS.convert(1,TimeUnit.MINUTES)
    return String.format("%2d:%2d", minutes, seconds)
}