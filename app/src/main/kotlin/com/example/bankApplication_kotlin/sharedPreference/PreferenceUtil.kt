package com.example.bankApplication_kotlin.sharedPreference

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context : Context) {
    private val loginPrefs : SharedPreferences = context.getSharedPreferences("login",0)
    fun getString(key : String,value:String):String
        =loginPrefs.getString(key,value).toString()

    fun setString(key : String,str:String){
        loginPrefs.edit().putString(key,str).apply()
    }

    fun getBoolean(key : String,value:Boolean):Boolean
        =loginPrefs.getBoolean(key,value)

    fun setBoolean(key : String,value:Boolean){
        loginPrefs.edit().putBoolean(key,value).apply()
    }
}
