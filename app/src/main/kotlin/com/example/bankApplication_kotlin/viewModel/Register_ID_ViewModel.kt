package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class Register_ID_ViewModel(application: Application) : AndroidViewModel(application) {
    var userID = MutableLiveData<String>()
    private val mApplication = application
    init {
        userID.value = ""
    }
    fun nextClick(){
        Toast.makeText(mApplication, "success", Toast.LENGTH_SHORT).show()
    }
    fun backClick(){
    }

}