package com.example.bankApplication_kotlin.sharedPreference

import android.content.Context
import android.content.SharedPreferences
//---------내부 저장소 관련 메소드 모음-----------
class PreferenceUtil(context : Context) {
    //로그인 화면 내부 저장 데이터
    private val loginPrefs : SharedPreferences = context.getSharedPreferences("login",0)
    private val registerPrefs : SharedPreferences = context.getSharedPreferences("register",0)
    fun loginGetString(key : String,value:String):String
        =loginPrefs.getString(key,value).toString()

    fun loginSetString(key : String,str:String){
        loginPrefs.edit().putString(key,str).apply()
    }
    fun loginGetBoolean(key : String,value:Boolean):Boolean
        =loginPrefs.getBoolean(key,value)

    fun loginSetBoolean(key : String,value:Boolean){
        loginPrefs.edit().putBoolean(key,value).apply()
    }
    fun registerGetString(key : String,value:String):String
        =registerPrefs.getString(key,value).toString()

    fun registerSetString(key : String,str:String){
        registerPrefs.edit().putString(key,str).apply()
    }
}
