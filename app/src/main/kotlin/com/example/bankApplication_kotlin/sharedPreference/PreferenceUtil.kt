package com.example.bankApplication_kotlin.sharedPreference

import android.content.Context
import android.content.SharedPreferences
//---------내부 저장소 관련 메소드 모음-----------
class PreferenceUtil(context : Context) {
    //로그인 화면 내부 저장 데이터
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
