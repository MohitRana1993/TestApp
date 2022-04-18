package com.mohit.bootesnull.localDatabase.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohit.bootesnull.localDatabase.DATABASE_NAME
import com.mohit.bootesnull.models.AlbumModelLocal
import com.mohit.bootesnull.models.PhotosModelLocal
import com.mohit.sampleapp.base.localDatabase.dao.DaoClass


@Database(
    entities = [
        AlbumModelLocal::class,
        PhotosModelLocal::class,
    ], version = 1, exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): DaoClass

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries()
                .build()
        }
    }
}