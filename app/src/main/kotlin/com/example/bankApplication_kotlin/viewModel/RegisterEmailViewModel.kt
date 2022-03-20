package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.event.Event

class RegisterEmailViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _see = MutableLiveData<Boolean>()
    val address = MutableLiveData<String>()
    val emailID = MutableLiveData<String>()
    val see : LiveData<Boolean>
        get() = _see
    val spinnerSelectedPosition = MutableLiveData<Int>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    init {
        emailID.value = ""
        address.value = ""
        _see.value = false
    }
    private val mApplication = application
    fun backClick(){
        _backEvent.value = Event(true)
    }
    fun nextClick(){
        Toast.makeText(mApplication, address.value, Toast.LENGTH_SHORT).show()
    }
    fun spinnerSelected(){

    }
}