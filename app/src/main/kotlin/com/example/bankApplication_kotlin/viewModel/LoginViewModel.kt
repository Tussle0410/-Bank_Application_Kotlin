package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import com.example.bankApplication_kotlin.event.Event

class LoginViewModel(application: Application) :AndroidViewModel(application){
    private val _saveIDCheck = MutableLiveData<Boolean>()
    private val _register = MutableLiveData<Event<Boolean>>()
    val saveID = MutableLiveData<String>()

    val saveIDCheck : LiveData<Boolean>
        get() = _saveIDCheck
    val register : LiveData<Event<Boolean>>
        get() = _register
    init {
        //PreferenceApplication 내부 저장소 값 가져오기
        _saveIDCheck.value = PreferenceApplication.prefs.getBoolean("saveIDCheck",false)
        saveID.value = PreferenceApplication.prefs.getString("saveID","")
    }
    private val mApplication = application
    //Register Button Click Event
    fun registerButtonClick(){
        _register.value = Event(true)
    }
    //SaveID CheckBox Click Event
    fun saveIDClick(){
        _saveIDCheck.value = _saveIDCheck.value!!.not()
    }
    //Login Button Click Event
    fun login(){
        PreferenceApplication.prefs.setBoolean("saveIDCheck",saveIDCheck.value!!)
        if(saveIDCheck.value!!)
            PreferenceApplication.prefs.setString("saveID",saveID.value!!)
        else
            PreferenceApplication.prefs.setString("saveID","")
    }
}