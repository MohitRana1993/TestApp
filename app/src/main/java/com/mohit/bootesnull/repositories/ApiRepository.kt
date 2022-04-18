package com.mohit.bootesnull.repositories


import androidx.lifecycle.MutableLiveData
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.mohit.bootesnull.apis.AlbumService
import com.mohit.bootesnull.models.EventDetailModel
import com.mohit.bootesnull.models.GetAllEventsModel
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiRepository {
    private var albumSearchService: AlbumService
    private val eventListLiveData: MutableLiveData<GetAllEventsModel> = MutableLiveData()
    private val eventDetailListLiveData: MutableLiveData<EventDetailModel> = MutableLiveData()


    fun eventListApi() {
        albumSearchService.getEvents("1", "1000", "")
            .enqueue(object : Callback<GetAllEventsModel> {
                override fun onResponse(
                    call: Call<GetAllEventsModel>,
                    response: Response<GetAllEventsModel>
                ) {
                    if (response.body() != null) {
                        eventListLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<GetAllEventsModel>, t: Throwable) {
                    eventListLiveData.postValue(null)
                }
            })
    }

    fun eventDetailApi(event_id: String) {
        albumSearchService.getEventDetail(event_id)
            .enqueue(object : Callback<EventDetailModel> {
                override fun onResponse(
                    call: Call<EventDetailModel>,
                    response: Response<EventDetailModel>
                ) {
                    if (response.body() != null) {
                        eventDetailListLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<EventDetailModel>, t: Throwable) {
                    eventDetailListLiveData.postValue(null)
                }
            })
    }


    fun getEventList(): MutableLiveData<GetAllEventsModel> {
        return eventListLiveData
    }

    fun getEventDetailList(): MutableLiveData<EventDetailModel> {
        return eventDetailListLiveData
    }


    companion object {
        private const val BASE_URL = "http://3.230.218.97:5001/api/v1/"
    }


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
        client.addInterceptor(Interceptor { chain ->
            var request = chain.request()
            request = request
                .newBuilder()
                .header("Accept", "application/vnd.yourapi.v1.full+json")
                .addHeader("api-key", "AFRO_2022-HJAKD-56464-V1-34512-SADFA_02")
                .addHeader(
                    "x-access-token",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjIzYTliOGQ1OWViMmQ3ZjIyNWM3NWQxIiwiaWF0IjoxNjUwMjk4MTkwfQ.ZyJQ2d5oEhOrG12jtQ5drtU4623pjayScNulO53yyOY"
                )
                .build()
            chain.proceed(request)
        })
        albumSearchService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumService::class.java)
    }

}


