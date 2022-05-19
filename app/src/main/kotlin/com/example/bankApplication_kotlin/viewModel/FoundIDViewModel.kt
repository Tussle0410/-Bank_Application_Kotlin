package com.example.bankApplication_kotlin.viewModel

import android.app.AlertDialog
import android.app.Application
import android.util.Log
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

class FoundIDViewModel(application: Application) : AndroidViewModel(application) {
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _foundEvent = MutableLiveData<Event<Boolean>>()
    private val pattern = "^[0-9]{8}"
    private val mApplication = application
    val name = MutableLiveData<String>()
    val birth = MutableLiveData<String>()
    var id = ArrayList<String>()
    val backEvent : LiveData<Event<Boolean>>
        get() = _backEvent
    val foundEvent : LiveData<Event<Boolean>>
        get() = _foundEvent
    init {
        name.value = ""
        birth.value = ""
    }
    //back Button Click Event
    fun backClick(){
        _backEvent.value = Event(true)
    }
    //found Button Click Event
    fun foundClick(){
        if(Pattern.matches(pattern,birth.value)){
            val api = FoundAPI.create()
            val name = name.value!!
            val birth = birth.value!!
            api.foundID(name,birth).enqueue(object : Callback<List<UserModel>>{
                override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>){
                    if (response.isSuccessful){
                        if (response.body()!![0].ID != ""){
                            for (value in response.body()!!){
                                id.add(value.ID)}
                            _foundEvent.value = Event(true)
                        }else{
                            Toast.makeText(mApplication,"해당 정보에 대한 아이디가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                    Log.d("FoundIDFail",t.message.toString())
                }
            })
        }else{
            Toast.makeText(mApplication,"생년월일을 확인해주세요.",Toast.LENGTH_SHORT).show()
        }
    }

}