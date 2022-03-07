package com.example.bankapplication_kotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(application: Application) :AndroidViewModel(application){
    var register_button:ObservableField<String> = ObservableField("Login")
    val mApplication = application
    fun registerClick(){
        Toast.makeText(mApplication,"hello register",Toast.LENGTH_SHORT).show()
    }
}