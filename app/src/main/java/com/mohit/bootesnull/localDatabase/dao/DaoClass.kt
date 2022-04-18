package com.mohit.sampleapp.base.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohit.bootesnull.models.AlbumModelLocal
import com.mohit.bootesnull.models.PhotosModelLocal

@Dao
interface DaoClass {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbums(item: AlbumModelLocal)



    @Query("SELECT*FROM AlbumData" )
    fun getAlbums(): List<AlbumModelLocal>

    @Query("DELETE FROM AlbumData")
    fun deleteAlbum()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(item: PhotosModelLocal)

    @Query("SELECT*FROM PhotoData" )
    fun getPhoto(): List<PhotosModelLocal>

    @Query("DELETE FROM PhotoData")
    fun deletePhoto()

//    @Query("UPDATE Aircrafts SET aircraft_id = :aircraft_id, type = :type, notes = :note   WHERE id = :id")
//    fun update(id: Int, aircraft_id: String, type: String, note: String)

//    @Query("SELECT*FROM FlightData WHERE user_id = :user_id and aircraftId = :aircraft_id")
//    fun get(user_id: String, aircraft_id: String): List<FlightsDataModel>

}