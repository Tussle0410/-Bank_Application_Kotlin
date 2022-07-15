package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event

class RemittanceHistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    //RemittanceHistory Back Button Click
    fun backClick(){
        _backEvent.value = Event(true)
    }
}