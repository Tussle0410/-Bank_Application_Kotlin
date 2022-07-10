package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication

class RemittanceViewModel(application: Application) :AndroidViewModel(application){
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _addressReceiver = MutableLiveData<Event<Boolean>>()
    private val _emailReceiver = MutableLiveData<Event<Boolean>>()
    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    private val _money= MutableLiveData<String>()
    private val _remittanceLimit = MutableLiveData<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val addressReceiver : LiveData<Event<Boolean>>
        get() = _addressReceiver
    val emailReceiver : LiveData<Event<Boolean>>
        get() = _emailReceiver
    val nextEvent : LiveData<Event<Boolean>>
        get() = _nextEvent
    val money : LiveData<String>
        get() = _money
    val remittanceLimit : LiveData<String>
        get() = _remittanceLimit
    init {
        _addressReceiver.value = Event(true)
        _money.value = PreferenceApplication.prefs.userGetString("money","")
        _remittanceLimit.value =
            (PreferenceApplication.prefs.userGetString("remittanceLimit","0").toLong() -
            PreferenceApplication.prefs.userGetString("currentRemittance","0").toLong()).toString()
    }
    //Back Button Click Method
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //ReceiverFrame addressReceiver Button Method
    fun addressReceiverClick(){
        if(_addressReceiver.value != Event(true))
            _addressReceiver.value = Event(true)
    }
    //ReceiverFrame emailReceiver Button Method
    fun emailReceiverClick(){
        if(_emailReceiver.value != Event(true))
            _emailReceiver.value = Event(true)
    }
    //Remittance Next Button Method
    fun nextClick(){
        _nextEvent.value = Event(true)
    }
}