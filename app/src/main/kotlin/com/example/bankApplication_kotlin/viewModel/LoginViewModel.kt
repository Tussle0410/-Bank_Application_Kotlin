package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication

class LoginViewModel(application: Application) :AndroidViewModel(application){
    private val _saveIDCheck = MutableLiveData<Boolean>()
    private val _register = MutableLiveData<Boolean>()
    val saveID = MutableLiveData<String>()

    val saveIDCheck : LiveData<Boolean>
        get() = _saveIDCheck
    val register : LiveData<Boolean>
        get() = _register
    init {
        _saveIDCheck.value = PreferenceApplication.prefs.getBoolean("saveIDCheck",false)
        saveID.value = PreferenceApplication.prefs.getString("saveID","")
        _register.value = false
    }
    private val mApplication = application
    fun registerClick(){
        _register.value = true
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