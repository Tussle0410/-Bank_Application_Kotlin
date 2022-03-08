package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication

class LoginViewModel(application: Application) :AndroidViewModel(application){
    var saveIDCheck = MutableLiveData<Boolean>()
    var saveID = MutableLiveData<String>()

    init {
        saveIDCheck.value = PreferenceApplication.prefs.getBoolean("saveIDCheck",false)
        saveID.value = PreferenceApplication.prefs.getString("saveID","")
    }
    private val mApplication = application
    fun registerClick(){
        Toast.makeText(mApplication,"hello register",Toast.LENGTH_SHORT).show()
    }
    fun login(){
        PreferenceApplication.prefs.setBoolean("saveIDCheck",saveIDCheck.value!!)
        if(saveIDCheck.value!!)
            PreferenceApplication.prefs.setString("saveID",saveID.value!!)
    }
}