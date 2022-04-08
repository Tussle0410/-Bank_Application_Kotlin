package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event
import java.util.regex.Pattern

class RegisterIDViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    private val pattern = "^[a-zA-Z0-9!?]{6,12}"
    val userID = MutableLiveData<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val nextEvent : LiveData<Event<Boolean>>
        get() = _nextEvent
    private val mApplication = application
    init {
        userID.value = ""
    }
    //Next Button Click Event
    fun nextClick(){
        if (Pattern.matches(pattern,userID.value)){
            _nextEvent.value = Event(true)
        }else{
            Toast.makeText(mApplication, "아이디는 대문자, 소문자, 숫자, !,?으로 \n" +
                    "6~12글자로 작성하셔야 합니다.", Toast.LENGTH_SHORT).show()
        }
    }
    //Back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }

}