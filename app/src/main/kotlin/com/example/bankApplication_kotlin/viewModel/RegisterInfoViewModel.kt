package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.app.DatePickerDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.event.Event
import java.util.*

class RegisterInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    private val _year = MutableLiveData<String>()
    private val _month = MutableLiveData<String>()
    private val _day = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val manCheck = MutableLiveData<Boolean>()
    val womanCheck = MutableLiveData<Boolean>()

    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val nextEvent : LiveData<Event<Boolean>>
        get() = _nextEvent
    val year : LiveData<String>
        get() = _year
    val month : LiveData<String>
        get() = _month
    val day : LiveData<String>
        get() = _day
    private val mApplication = application
    init {
        name.value = ""
        manCheck.value = true
        womanCheck.value = false
        _year.value = "1997"
        _month.value = "04"
        _day.value = "10"
    }
    //Back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //Next Button Click Event
    fun nextClick(){
        _nextEvent.value = Event(true)
    }
    //Birth Button Click Event
    /*fun birthClick(){
        val c = Calendar.getInstance()
        val dialog = DatePickerDialog(mApplication, android.R.style.Theme_Holo_Light_Dialog,
        DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
            _year.value = y.toString()
            _month.value = m.toString()
            _day.value = d.toString()
        }{
        })
    }*/
}