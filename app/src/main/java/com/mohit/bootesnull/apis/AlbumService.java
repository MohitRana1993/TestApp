package com.mohit.bootesnull.apis;

import com.mohit.bootesnull.models.AlbumModelLocal;
import com.mohit.bootesnull.models.EventDetailModel;
import com.mohit.bootesnull.models.GetAllEventsModel;
import com.mohit.bootesnull.models.PhotosModelLocal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlbumService {




    @GET("events")
    Call<GetAllEventsModel>getEvents(
            @Query("page") String page,
            @Query("limit") String limit,
            @Query("search") String search
    );

    @GET("event")
    Call<EventDetailModel>getEventDetail(
            @Query("event_id") String event_id

    );





}
