package com.example.bankApplication_kotlin.sharedPreference

import android.app.Application
//--------어플 내장 저장소 관련 호출---------
class PreferenceApplication : Application() {
    companion object{
        lateinit var prefs : PreferenceUtil
        //Database IP Input..
        var ServerIP = ""
    }
    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}