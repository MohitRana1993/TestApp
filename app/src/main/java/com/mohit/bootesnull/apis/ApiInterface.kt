package com.mohit.bootesnull.apis

import com.mohit.bootesnull.models.PhotosModelLocal
  import retrofit2.Call
import retrofit2.http.*


interface
ApiInterface {


    @GET("photos")
    fun photosApi(@Query("albumId")albumId:String): Call<List<PhotosModelLocal>>

}

