package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.api.LoginAPI
import com.example.bankApplication_kotlin.api.model.UserModel
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import com.example.bankApplication_kotlin.event.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) :AndroidViewModel(application){
    private val _saveIDCheck = MutableLiveData<Boolean>()
    private val _register = MutableLiveData<Event<Boolean>>()
    private val _found = MutableLiveData<Event<Boolean>>()
    private val _loginEvent = MutableLiveData<Event<Boolean>>()
    val userID = MutableLiveData<String>()
    val userPW = MutableLiveData<String>()
    val loginEvent : LiveData<Event<Boolean>>
        get() = _loginEvent
    val saveIDCheck : LiveData<Boolean>
        get() = _saveIDCheck
    val register : LiveData<Event<Boolean>>
        get() = _register
    val found : LiveData<Event<Boolean>>
        get() = _found
    init {
        //PreferenceApplication 내부 저장소 값 가져오기
        _saveIDCheck.value = PreferenceApplication.prefs.loginGetBoolean("saveIDCheck",false)
        userID.value = PreferenceApplication.prefs.loginGetString("saveID","")
        userPW.value = ""
    }
    private val mApplication = application
    //Register Button Click Event
    fun registerButtonClick(){
        _register.value = Event(true)
    }
    //Found Button Click Event
    fun foundButtonClick(){
        _found.value = Event(true)
    }
    //SaveID CheckBox Click Event
    fun saveIDClick(){
        _saveIDCheck.value = _saveIDCheck.value!!.not()
    }
    //Login Button Click Event
    fun login(){
        PreferenceApplication.prefs.loginSetBoolean("saveIDCheck",saveIDCheck.value!!)
        if(saveIDCheck.value!!)
            PreferenceApplication.prefs.loginSetString("saveID",userID.value!!)
        else
            PreferenceApplication.prefs.loginSetString("saveID","")
        val api = LoginAPI.create()
        val id = userID.value!!
        val pw = userPW.value!!
        Log.d("뭐양이거 ", "$id $pw")
        api.login(id,pw).enqueue(object : Callback<List<UserModel>>{
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if(response.isSuccessful){
                    Log.d("뭐야 이거" , response.body()!![0].toString())
                    if(response.body()!![0].ID == "")
                        Toast.makeText(mApplication,"로그인의 실패하였습니다.",Toast.LENGTH_SHORT).show()
                    else{
                        PreferenceApplication.prefs.registerSetString("ID",response.body()!![0].ID)
                        PreferenceApplication.prefs.registerSetString("Name",response.body()!![0].Name)
                        PreferenceApplication.prefs.registerSetString("Birth",response.body()!![0].Birth)
                        PreferenceApplication.prefs.registerSetString("Gender",response.body()!![0].Gender)
                        PreferenceApplication.prefs.registerSetString("Email",response.body()!![0].Email)
                        _loginEvent.value = Event(true)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.d("LoginFail", t.message.toString())
            }
        })
    }
}