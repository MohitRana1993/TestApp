package com.mohit.bootesnull.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PhotoData")
class PhotosModelLocal(
    @ColumnInfo(name = "albumId") var albumId: Int,
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "thumbnailUrl") var thumbnailUrl: String,
) {
    @PrimaryKey(autoGenerate = true)
    var idd: Int = 0
}

