package com.mohit.bootesnull.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mohit.bootesnull.models.GetAllEventsModel
import com.mohit.bootesnull.repositories.ApiRepository

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private var apiRepository: ApiRepository? = null
    var mutableLiveData: MutableLiveData<GetAllEventsModel>? = null

    fun init() {
        apiRepository = ApiRepository()
        mutableLiveData = apiRepository!!.getEventList()
    }

    fun eventListApi() {
        apiRepository!!.eventListApi()
    }
}