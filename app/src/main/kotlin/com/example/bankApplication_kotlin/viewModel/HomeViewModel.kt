package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.api.HomeAPI
import com.example.bankApplication_kotlin.api.model.AddressModel
import com.example.bankApplication_kotlin.api.model.BannerModel
import com.example.bankApplication_kotlin.api.model.HomeNaviMenu
import com.example.bankApplication_kotlin.event.Event
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import com.google.android.material.navigation.NavigationBarView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _getBannerEvent = MutableLiveData<Event<Boolean>>()
    private val _getAssetEvent = MutableLiveData<Event<Boolean>>()
    private val _currentFragment = MutableLiveData(HomeNaviMenu.HomeFragment)
    private val _homeCurMoney = MutableLiveData<String>()
    private val _userName = MutableLiveData<String>()
    private val _userID = MutableLiveData<String>()
    private val _myAssetMoney = MutableLiveData<String>()
    private val _deposit = MutableLiveData<String>()
    private val _savings = MutableLiveData<String>()
    private val _loan = MutableLiveData<String>()
    private val _fund = MutableLiveData<String>()
    private val _myAssetMoneyCheck = MutableLiveData<Boolean>()
    private val _myAssetRefresh = MutableLiveData<Event<Boolean>>()
    private val _remittanceEvent = MutableLiveData<Event<Boolean>>()
    val balanceShow = MutableLiveData<Boolean>()
    val userID : LiveData<String>
        get() = _userID
    val getBannerEvent : LiveData<Event<Boolean>>
        get() = _getBannerEvent
    val getAssetEvent : LiveData<Event<Boolean>>
        get() = _getAssetEvent
    val myAssetRefresh : LiveData<Event<Boolean>>
        get() = _myAssetRefresh
    val userName : LiveData<String>
        get() = _userName
    val homeCurMoney : LiveData<String>
        get() = _homeCurMoney
    var eventBanner = ArrayList<String>()
    var financialBanner = ArrayList<String>()
    var myAsset = HashMap<String,Int>().apply {
        this["savings"] = 0
        this["deposit"] = 0
        this["loan"] = 0
        this["fund"] = 0
    }
    val myAssetMoney : LiveData<String>
        get() = _myAssetMoney
    val myAssetMoneyCheck : LiveData<Boolean>
        get() = _myAssetMoneyCheck
    val deposit : MutableLiveData<String>
        get() = _deposit
    val savings : MutableLiveData<String>
        get() = _savings
    val loan : MutableLiveData<String>
        get() = _loan
    val fund : MutableLiveData<String>
        get() = _fund
    val currentFragment : LiveData<HomeNaviMenu>
        get() = _currentFragment
    val remittanceEvent : LiveData<Event<Boolean>>
        get() = _remittanceEvent
    val onItemSelectedListener =
        NavigationBarView.OnItemSelectedListener { item ->
            val fragment = getFragment(item.itemId)
            changeCurrentFragment(fragment)
            true
        }
    init {
        _homeCurMoney.value = PreferenceApplication.prefs.userGetString("money","")
        _userName.value = PreferenceApplication.prefs.userGetString("Name","")
        _userID.value = PreferenceApplication.prefs.userGetString("ID","")
        _deposit.value = "0"
        _savings.value = "0"
        _loan.value = "0"
        _fund.value = "0"
        _myAssetMoney.value = "0"
        _myAssetMoneyCheck.value = true
        balanceShow.value = true
    }
    //Banner Info 데이터베이스에서 가져오는 함수
    fun getBannerInfo() {
        val api = HomeAPI.create()
        api.getBanner().enqueue(object : Callback<List<BannerModel>>{
            override fun onResponse(call: Call<List<BannerModel>>, response: Response<List<BannerModel>>) {
                if(response.isSuccessful){
                    for(info in response.body()!!){
                        if (info.kinds == "event")
                            eventBanner.add(info.bannerRoute)
                        else
                            financialBanner.add(info.bannerRoute)
                    }
                    _getBannerEvent.value = Event(true)
                }
            }
            override fun onFailure(call: Call<List<BannerModel>>, t: Throwable) {
                Log.d("getBannerFail", t.message.toString())
            }
        })
    }
    //Bottom Navigation Fragment Setting
    private fun getFragment(menu_id: Int) : HomeNaviMenu
        = when(menu_id){
            R.id.nav_home -> HomeNaviMenu.HomeFragment
            R.id.nav_financial_products -> HomeNaviMenu.FinancialFragment
            R.id.nav_myBank -> HomeNaviMenu.MyBankFragment
            R.id.nav_myAsset -> HomeNaviMenu.MyAssetFragment
            else -> throw IllegalArgumentException("not found menu item id")
        }
    //Bottom Navigation Fragment Change
    private fun changeCurrentFragment(fragment : HomeNaviMenu){
        if (_currentFragment.value == fragment)
            return
        else
            _currentFragment.value = fragment
    }
    fun myAssetRefreshClick(){
        _myAssetRefresh.value = Event(true)
    }
    //Get user Asset Info 데이터베이스에 가져오는 함수
    fun getMyAssetInfo(){
        val api = HomeAPI.create()
        api.getMyAsset(userID.value!!).enqueue(object : Callback<List<AddressModel>>{
            override fun onResponse(call: Call<List<AddressModel>>, response: Response<List<AddressModel>>) {
                if(response.isSuccessful){
                    val size = response.body()!!.size
                    var sum = 0
                    for (i in 0 until size){
                        val key = response.body()!![i].kinds
                        val value = response.body()!![i].money.toInt()
                        if(key == "loan")
                            sum -= value
                        else
                            sum += value
                        myAsset[key] = value
                    }
                    moneyInit()
                    _myAssetMoneyCheck.value = sum>=0
                    _myAssetMoney.value = sum.toString()
                    _getAssetEvent.value = Event(true)
                }
            }
            override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                Log.d("getMyAsset Fail", t.message.toString())
            }
        })
    }
    //money attribute Init Method
    fun moneyInit(){
        _deposit.value = myAsset["deposit"].toString()
        _savings.value = myAsset["savings"].toString()
        _loan.value = myAsset["loan"].toString()
        _fund.value = myAsset["fund"].toString()
    }
    //Balance Refresh Button Click Event
    fun refreshClick(){
        val api = HomeAPI.create()
        api.getCurMoney(PreferenceApplication.prefs.userGetString("ID","")).enqueue(
            object : Callback<List<AddressModel>>{
                override fun onResponse(call: Call<List<AddressModel>>, response: Response<List<AddressModel>>) {
                    if(response.isSuccessful){
                        _homeCurMoney.value = response.body()!![0].money
                        PreferenceApplication.prefs.userSetString("money",_homeCurMoney.value!!)
                    }
                }

                override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                    Log.d("refresh Fail" , t.message.toString())
                }
            }
        )
    }
    //RemittanceButton Click Event
    fun remittanceClick(){
        _remittanceEvent.value = Event(true)
    }
}