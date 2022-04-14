package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import java.util.*

class RegisterInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    private val _birthEvent = MutableLiveData<Event<Boolean>>()
    private val _manCheck = MutableLiveData<Boolean>()
    private val _womanCheck = MutableLiveData<Boolean>()
    val year = MutableLiveData<String>()
    val month = MutableLiveData<String>()
    val day = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val infoAgreeCheck = MutableLiveData<Boolean>()

    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val nextEvent : LiveData<Event<Boolean>>
        get() = _nextEvent
    val birthEvent : LiveData<Event<Boolean>>
        get() = _birthEvent
    val manCheck : LiveData<Boolean>
        get() = _manCheck
    val womanCheck : LiveData<Boolean>
        get() = _womanCheck
    private val mApplication = application
    init {
        name.value = ""
        _manCheck.value = true
        _womanCheck.value = false
        year.value = "1997"
        month.value = "04"
        day.value = "10"
    }
    //man CheckBox Click
    fun manCheckBoxClick(){
        _manCheck.value = true
        _womanCheck.value = false
    }
    //woman CheckBox Click
    fun womanCheckBoxClick(){
        _womanCheck.value = true
        _manCheck.value = false
    }
    //Back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //Next Button Click Event
    fun nextClick(){
        if(infoAgreeCheck.value == true){
            val birth = year.value + month.value + day.value
            val sex = if(manCheck.value!!)
                "남"
            else
                "여"
            PreferenceApplication.prefs.registerSetString("name",name.value!!)
            PreferenceApplication.prefs.registerSetString("sex",sex)
            PreferenceApplication.prefs.registerSetString("birth",birth)
            _nextEvent.value = Event(true)
        }else
            Toast.makeText(mApplication,"개인정보 사용 동의를 체크해주시기 바랍니다.",Toast.LENGTH_SHORT).show()

    }
    //Birth Button Click Event
    fun birthClick(){
        _birthEvent.value = Event(true)
    }
}
