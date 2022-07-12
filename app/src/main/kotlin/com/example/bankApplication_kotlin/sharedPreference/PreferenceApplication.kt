package com.example.bankApplication_kotlin.sharedPreference

import android.app.Application
//--------어플 내장 저장소 관련 호출---------
class PreferenceApplication : Application() {
    companion object{
        lateinit var prefs : PreferenceUtil
        var ServerIP = "172.30.1.39"
    }
    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}