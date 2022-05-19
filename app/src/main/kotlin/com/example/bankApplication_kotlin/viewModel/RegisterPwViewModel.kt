package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.api.RegisterAPI
import com.example.bankApplication_kotlin.api.model.UserModel
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern
import kotlin.random.Random

class RegisterPwViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _registerEvent = MutableLiveData<Event<Boolean>>()
    private val _pwShow = MutableLiveData<Boolean>()
    private val pattern = "^[a-zA-Z0-9!?]{8,20}"        //PW regex
    private val mApplication = application
    val passWord = MutableLiveData<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val registerEvent : LiveData<Event<Boolean>>
        get() = _registerEvent
    val pwShow : LiveData<Boolean>
        get() = _pwShow
    init {
        passWord.value = ""
        _pwShow.value = false
    }
    //passWord Show Check Box Click
    fun pwShowClick(){
        _pwShow.value = _pwShow.value!!.not()
    }
    //back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //register Button Click Event
    fun registerClick(){
        if(Pattern.matches(pattern,passWord.value)){
            val id = PreferenceApplication.prefs.registerGetString("ID","")
            val pw = passWord.value!!
            val name = PreferenceApplication.prefs.registerGetString("Name","")
            val gender = PreferenceApplication.prefs.registerGetString("Gender","")
            val email = PreferenceApplication.prefs.registerGetString("Email","")
            val birth = PreferenceApplication.prefs.registerGetString("Birth","")
            val address = createAccountAddress()
            val api = RegisterAPI.create()
            api.register(id,pw,name,gender,email,birth,address)
                .enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.isSuccessful){
                            PreferenceApplication.prefs.registerInit()
                            _registerEvent.value = Event(true)
                        }
                    }
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("registerFail",t.message.toString())
                    }
                })
        }else
            Toast.makeText(mApplication,"비밀번호 규정을 확인해주세요.",Toast.LENGTH_SHORT).show()
    }
    //AccountAddress Create Method
    fun createAccountAddress() : String{
        var address = ""
        for(i in 1..13){
            address += (0..9).random().toString()
        }
        val api = RegisterAPI.create()
        api.regAccountAddressCheck(address).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    if(response.body()=="false"){
                        address = createAccountAddress()
                    }
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("accountAddressCheckFail", t.message.toString())
            }
        })
        return address
    }
}