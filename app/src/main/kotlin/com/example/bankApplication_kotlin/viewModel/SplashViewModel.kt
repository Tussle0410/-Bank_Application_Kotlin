package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    val isSplashing : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    //---------Splash Page 3 second Wait---------
    init {
        viewModelScope.launch(Dispatchers.IO){
            delay(3000)
            isSplashing.postValue(true)
        }
    }
}