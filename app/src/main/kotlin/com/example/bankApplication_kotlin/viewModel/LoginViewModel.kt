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
        _saveIDCheck.value = PreferenceApplication.prefs.getBoolean("saveIDCheck",false)
        saveID.value = PreferenceApplication.prefs.getString("saveID","")
    }
    private val mApplication = application
    fun registerButtonClick(){
        _register.value = Event(true)
    }
    fun saveIDClick(){
        _saveIDCheck.value = _saveIDCheck.value!!.not()
    }
    fun login(){
        PreferenceApplication.prefs.setBoolean("saveIDCheck",saveIDCheck.value!!)
        if(saveIDCheck.value!!)
            PreferenceApplication.prefs.setString("saveID",saveID.value!!)
        else
            PreferenceApplication.prefs.setString("saveID","")
    }
}