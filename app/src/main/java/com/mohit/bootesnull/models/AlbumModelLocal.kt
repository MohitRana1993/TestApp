package com.mohit.bootesnull.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AlbumData")
class AlbumModelLocal(
    @ColumnInfo(name = "userId") var userId: Int,
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
) {
    @PrimaryKey(autoGenerate = true)
    var idd: Int = 0
}

