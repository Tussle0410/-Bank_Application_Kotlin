package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event

class RegisterPwViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _registerEvent = MutableLiveData<Event<Boolean>>()
    private val _pwShow = MutableLiveData<Boolean>()
    val passWord = MutableLiveData<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val registerEvent : LiveData<Event<Boolean>>
        get() = _registerEvent
    val pwShow : LiveData<Boolean>
        get() = _pwShow
    init {
        passWord.value = ""
        _pwShow.value = false
    }
    //passWord Show Check Box Click
    fun pwShowClick(){
        _pwShow.value = _pwShow.value!!.not()
        if(_pwShow.value == true){

        }else{

        }
    }
    //back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //register Button Click Event
    fun registerClick(){

    }
}