package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.api.HomeAPI
import com.example.bankApplication_kotlin.api.LoginAPI
import com.example.bankApplication_kotlin.api.model.AddressModel
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
        //로그인 성공시 유저 정보를 PreferenceApplication 저장합니다.
        api.login(id,pw).enqueue(object : Callback<List<UserModel>>{
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if(response.isSuccessful){
                    if(response.body()!![0].ID == "")
                        Toast.makeText(mApplication,"로그인의 실패하였습니다.",Toast.LENGTH_SHORT).show()
                    else{
                        PreferenceApplication.prefs.userSetString("ID",response.body()!![0].ID)
                        PreferenceApplication.prefs.userSetString("Name",response.body()!![0].Name)
                        PreferenceApplication.prefs.userSetString("Birth",response.body()!![0].Birth)
                        PreferenceApplication.prefs.userSetString("Gender",response.body()!![0].Gender)
                        PreferenceApplication.prefs.userSetString("Email",response.body()!![0].Email)
                        getAddressInfo()
                    }
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.d("LoginFail", t.message.toString())
            }
        })
    }
    //로그인 유저 Main Address Info 가져오기
    //가져온 정보는 PreferenceApplication 저장합니다.
    fun getAddressInfo(){
        val api = LoginAPI.create()
        val id = PreferenceApplication.prefs.userGetString("ID","")
        api.getMainAddress(id).enqueue(object : Callback<List<AddressModel>>{
            override fun onResponse(call: Call<List<AddressModel>>, response: Response<List<AddressModel>>) {
                if(response.isSuccessful){
                    PreferenceApplication.prefs.userSetString("money",response.body()!![0].money)
                    PreferenceApplication.prefs.userSetString("address",response.body()!![0].address)
                    PreferenceApplication.prefs.userSetString("remittanceLimit",response.body()!![0].remittanceLimit)
                    PreferenceApplication.prefs.userSetString("currentRemittance",response.body()!![0].currentRemittance)
                    PreferenceApplication.prefs.userSetString("addressName",response.body()!![0].addressName)
                    _loginEvent.value = Event(true)
                }
            }
            override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                Log.d("getAddressInfo Fail",t.message.toString(),)
            }
        })
    }
}