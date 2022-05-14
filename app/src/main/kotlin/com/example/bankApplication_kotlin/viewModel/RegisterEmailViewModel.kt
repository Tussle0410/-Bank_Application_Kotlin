package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.api.RegisterAPI
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterEmailViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    private val _see = MutableLiveData<Boolean>()
    val address = MutableLiveData<String>()
    val emailID = MutableLiveData<String>()
    private val pattern = Patterns.EMAIL_ADDRESS
    val see : LiveData<Boolean>
        get() = _see
    val spinnerSelectedPosition = MutableLiveData<Int>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val nextEvent : LiveData<Event<Boolean>>
        get() = _nextEvent
    init {
        emailID.value = ""
        address.value = ""
        _see.value = false
    }
    private val mApplication = application
    //Address Spinner ItemClick Listener
    val clickListener =
    object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val temp = parent!!.getItemAtPosition(position) as String
            if (temp == "직접 입력"){
                _see.value = true
                address.value = ""
            }
            else{
                address.value = temp
                _see.value = false
            }

        }
    }
    //Back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //Next Button Click Event
    fun nextClick(){
        val email = emailID.value + "@" + address.value
        val matcher = pattern.matcher(email)
        if(matcher.find()){
            val api = RegisterAPI.create()
            api.regCheckEmail(email).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful) {
                        if (response.body() == "true") {
                            PreferenceApplication.prefs.registerSetString("Email", email)
                            _nextEvent.value = Event(true)
                        } else {
                            Toast.makeText(mApplication, "이미 등록된 이메일입니다.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("CheckEmailFail",t.message.toString())
                }
            })
        }
        else
            Toast.makeText(mApplication,"이메일 형식을 확인해주시기 바랍니다.",Toast.LENGTH_SHORT).show()
    }
}