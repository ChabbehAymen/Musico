package com.example.musico.placeholder

import com.example.musico.SongStateUi
import java.util.ArrayList

object HistoryItemPlaceHolder {

    /**
     * An array of sample (placeholder) items.
     */
    val Music: MutableList<SongStateUi> = ArrayList()


    init {
        // Add some sample items.
        for (ITEM in Music) {
            addItem(createPlaceholderItem(ITEM))
        }
    }

    fun createPlaceholderItem(ITEM: SongStateUi): SongStateUi {
        return SongStateUi(
            title = ITEM.title,
            id = ITEM.id,
            coverUri = ITEM.coverUri,
            artist = ITEM.artist,
            duration = ITEM.duration,
            path = ITEM.path
        )
    }

    fun addItem(item: SongStateUi) {
        Music.add(item)
    }
}