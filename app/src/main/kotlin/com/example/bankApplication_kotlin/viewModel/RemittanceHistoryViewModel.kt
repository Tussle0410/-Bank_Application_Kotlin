package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.api.RemittanceAPI
import com.example.bankApplication_kotlin.api.model.HistoryModel
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemittanceHistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _history = MutableLiveData<List<HistoryModel>>()
    private val _addressName = MutableLiveData<String>()
    private val _money = MutableLiveData<String>()
    private val _address = MutableLiveData<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val history : LiveData<List<HistoryModel>>
        get() = _history
    val addressName : LiveData<String>
        get() = _addressName
    val address : LiveData<String>
        get() = _address
    val money : LiveData<String>
        get() = _money
    init {
        _address.value = PreferenceApplication.prefs.userGetString("address","")
        _addressName.value = PreferenceApplication.prefs.userGetString("addressName","")
        _money.value = PreferenceApplication.prefs.userGetString("money","")
    }
    //RemittanceHistory Back Button Click
    fun backClick(){
        _backEvent.value = Event(true)
    }
    fun setHistory(){
        val api = RemittanceAPI.create()
        val address = PreferenceApplication.prefs.userGetString("address","")
        api.getHistory(address).enqueue(object : Callback<List<HistoryModel>>{
            override fun onResponse(call: Call<List<HistoryModel>>, response: Response<List<HistoryModel>>) {
                if(response.isSuccessful){
                    _history.value = response.body()
                }
            }
            override fun onFailure(call: Call<List<HistoryModel>>, t: Throwable) {
                Log.d("getHistoryFail", t.message.toString())
            }
        })
    }
}