package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.api.RemittanceAPI
import com.example.bankApplication_kotlin.api.model.AddressModel
import com.example.bankApplication_kotlin.api.model.UserModel
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemittanceViewModel(application: Application) :AndroidViewModel(application){
    private val mApplication = application
    private val _backEvent = MutableLiveData<Event<Boolean>>()
    private val _addressReceiver = MutableLiveData<Event<Boolean>>()
    private val _emailReceiver = MutableLiveData<Event<Boolean>>()
    private val _receiverKinds = MutableLiveData<Boolean>()
    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    private val _money= MutableLiveData<String>()
    private val _remittanceLimit = MutableLiveData<String>()
    private val _receiverAddressName = MutableLiveData<String>()
    val receiverAddress = MutableLiveData<String>()
    val receiverEmail = MutableLiveData<String>()
    val receiverName = MutableLiveData<String>()
    val amount = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
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
    val receiverAddressName : LiveData<String>
        get() = _receiverAddressName
    init {
        _addressReceiver.value = Event(true)
        _money.value = PreferenceApplication.prefs.userGetString("money","")
        _receiverKinds.value = true
        receiverAddress.value = ""
        receiverEmail.value = ""
        receiverName.value = ""
        amount.value = "0"
        pw.value = ""
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
        if(_addressReceiver.value != Event(true)){
            _addressReceiver.value = Event(true)
            _receiverKinds.value = true
        }
    }
    //ReceiverFrame emailReceiver Button Method
    fun emailReceiverClick(){
        if(_emailReceiver.value != Event(true)){
            _emailReceiver.value = Event(true)
            _receiverKinds.value = false
        }

    }
    //Remittance Next Button Method
    fun nextClick(){
        _nextEvent.value = Event(true)
    }
    //Remittance Receiver Address Check Method
    fun addressCheck() : Boolean{
        val address = receiverAddress.value
        if (address!!.length!=13){
            Toast.makeText(mApplication,"계좌번호를 13글자로 작성해주세요.",Toast.LENGTH_SHORT).show()
            return false
        }
        var check = false
        val api = RemittanceAPI.create()
        if(_receiverKinds.value == true){
            api.remittanceAddressCheck(address!!).enqueue(object  : Callback<List<AddressModel>>{
                override fun onResponse(call: Call<List<AddressModel>>, response: Response<List<AddressModel>>) {
                    if(response.isSuccessful){
                        if(response.body()!!.isNotEmpty()){
                            check = true
                            receiverName.value = response.body()!![0].Name
                            _receiverAddressName.value = response.body()!![0].addressName
                        }else{
                            Toast.makeText(mApplication,"해당 계좌는 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                    Log.d("addressCheck Fail", t.message.toString())
                }

            })
        }else{
            val email = receiverEmail.value
            val matcher = Patterns.EMAIL_ADDRESS.matcher(email)
            if (!matcher.find()){
                Toast.makeText(mApplication,"이메일 형식으로 작성해주세요.",Toast.LENGTH_SHORT).show()
                return false
            }
            val name = receiverName.value
            api.remittanceEmailCheck(email!!, name!!).enqueue(object : Callback<List<AddressModel>>{
                override fun onResponse(call: Call<List<AddressModel>>, response: Response<List<AddressModel>>) {
                    if(response.isSuccessful){
                        if(response.body()!!.isNotEmpty()){
                            check = true
                            _receiverAddressName.value = response.body()!![0].addressName
                        } else
                            Toast.makeText(mApplication,"해당 정보와 유효한 계좌가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                    Log.d("EmailCheck Fail",t.message.toString())
                }
            })
        }
        return check
    }
    //Amount plus Button
    fun plus(check : String){
        var tempAmount = amount.value!!.toLong() + check.toLong()
        if(tempAmount <= money.value!!.toLong())
            amount.value = tempAmount.toString()
    }
    //Remittance Limit Check Method
    fun amountCheck() : Boolean{
        if(amount.value!!.toLong() <= _remittanceLimit.value!!.toLong())
            return true
        Toast.makeText(mApplication,"송금한도를 초과한 금액입니다.",Toast.LENGTH_SHORT).show()
        return false
    }
}