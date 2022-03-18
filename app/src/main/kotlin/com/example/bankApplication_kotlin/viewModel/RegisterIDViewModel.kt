package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event

class RegisterIDViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val userID = MutableLiveData<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    private val mApplication = application
    init {
        userID.value = ""
    }
    fun nextClick(){
        Toast.makeText(mApplication, userID.value, Toast.LENGTH_SHORT).show()
    }
    fun backClick(){
        _backEvent.value = Event(true)
    }

}