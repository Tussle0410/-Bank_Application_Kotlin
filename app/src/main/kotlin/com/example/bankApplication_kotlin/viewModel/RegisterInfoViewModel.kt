package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.app.DatePickerDialog
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import java.util.*
import java.util.regex.Pattern

class RegisterInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    private val _birthEvent = MutableLiveData<Event<Boolean>>()
    private val _manCheck = MutableLiveData<Boolean>()
    private val _womanCheck = MutableLiveData<Boolean>()
    private val pattern = "^[a-zA-Z가-핳]{1,10}"
    private val _infoAgreeCheck = MutableLiveData<Boolean>()
    val year = MutableLiveData<String>()
    val month = MutableLiveData<String>()
    val day = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val infoAgreeCheck : LiveData<Boolean>
        get() = _infoAgreeCheck

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
        _infoAgreeCheck.value = false
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
    //infoAgree CheckBox Click Event
    fun infoAgreeClick(){
        _infoAgreeCheck.value = _infoAgreeCheck.value!!.not()
    }
    //Next Button Click Event
    fun nextClick(){
        if(Pattern.matches(pattern,name.value)){
            if(infoAgreeCheck.value == true){
                if(limit()){
                    val birth = year.value + month.value + day.value
                    val sex = if(manCheck.value!!)
                        "남"
                    else
                        "여"
                    PreferenceApplication.prefs.registerSetString("Name",name.value!!)
                    PreferenceApplication.prefs.registerSetString("Gender",sex)
                    PreferenceApplication.prefs.registerSetString("Birth",birth)
                    _nextEvent.value = Event(true)
                }else
                    Toast.makeText(mApplication,"생년월일을 확인해주세요.",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(mApplication,"개인정보 사용 동의를 체크해주시기 바랍니다.",Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(mApplication,"이름을 정확히 입력해주세요", Toast.LENGTH_SHORT).show()


    }
    //Birth limit Check
    private fun limit():Boolean{
        val c = Calendar.getInstance()
        val tempYear = c.get(Calendar.YEAR)
        val tempMonth = c.get(Calendar.MONTH)
        val tempDay = c.get(Calendar.DAY_OF_MONTH)
        if(year.value!!.toInt() > tempYear) {
            return false
        }else if (year.value!!.toInt() == tempYear){
            if(month.value!!.toInt() > tempMonth+1) {
                return false
            }else if(month.value!!.toInt()==tempMonth+1){
                if(day.value!!.toInt() > tempDay) {
                    return false
                }
            }
        }
        return true
    }
    //Birth Button Click Event
    fun birthClick(){
        _birthEvent.value = Event(true)
    }
}
