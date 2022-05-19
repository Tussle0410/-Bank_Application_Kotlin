package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.api.FoundAPI
import com.example.bankApplication_kotlin.api.model.UserModel
import com.example.bankApplication_kotlin.event.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class FoundPWViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _foundIDEvent = MutableLiveData<Event<Boolean>>()
    private val _foundPWEvent = MutableLiveData<Event<Boolean>>()
    private val idPatterns = "^[a-zA-Z0-9!?]{6,12}"
    private val emailPatterns = Patterns.EMAIL_ADDRESS
    private val mApplication = application
    lateinit var pw : String
    val userID = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val foundIDEvent : LiveData<Event<Boolean>>
        get() = _foundIDEvent
    val foundPWEvent : LiveData<Event<Boolean>>
        get() = _foundPWEvent
    init {
        userID.value = ""
        email.value = ""
    }
    //Back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //foundID Button Click Event
    fun foundIDClick(){
        _foundIDEvent.value = Event(true)
    }
    //foundPW Button Click Event
    fun foundPWClick(){
        if(Pattern.matches(idPatterns,userID.value)){
            val matcher = emailPatterns.matcher(email.value)
            if(matcher.find()){
                val api = FoundAPI.create()
                val id = userID.value!!
                val email = email.value!!
                api.foundPW(id,email).enqueue(object : Callback<List<UserModel>>{
                    override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                        if(response.isSuccessful){
                            if(response.body()!![0].PW == ""){
                                Toast.makeText(mApplication,"해당 아이디와 이메일은 일치하지 않습니다. ",Toast.LENGTH_SHORT).show()
                            }else{
                                pw = response.body()!![0].PW
                                _foundPWEvent.value = Event(true)
                            }
                        }
                    }
                    override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                        Log.d("FoundPWFail", t.message.toString())
                    }
                })
            }else{
                Toast.makeText(mApplication,"이메일을 확인해주세요.",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(mApplication,"아이디를 확인해주세요.",Toast.LENGTH_SHORT).show()
        }
    }
}