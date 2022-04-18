package com.mohit.bootesnull.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mohit.bootesnull.models.EventDetailModel
import com.mohit.bootesnull.repositories.ApiRepository

class EventDetailViewModel(application: Application) : AndroidViewModel(application) {
    private var apiRepository: ApiRepository? = null
    var mutableLiveData: MutableLiveData<EventDetailModel>? = null

    fun init() {
        apiRepository = ApiRepository()
        mutableLiveData = apiRepository!!.getEventDetailList()

    }

    fun eventListApi(event_id: String) {
         apiRepository!!.eventDetailApi(event_id)
    }
}