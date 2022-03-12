package com.example.bankApplication_kotlin.sharedPreference

import android.app.Application

class PreferenceApplication : Application() {
    companion object{
        lateinit var prefs : PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}