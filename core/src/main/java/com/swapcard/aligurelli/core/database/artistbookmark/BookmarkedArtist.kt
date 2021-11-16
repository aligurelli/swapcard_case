
package com.swapcard.aligurelli.core.database.artistbookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist_bookmarked")
data class BookmarkedArtist(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "disambiguation") val disambiguation: String,

)
