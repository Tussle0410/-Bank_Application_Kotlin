package com.example.bankApplication_kotlin.sharedPreference

import android.content.Context
import android.content.SharedPreferences
//---------내부 저장소 관련 메소드 모음-----------
class PreferenceUtil(context : Context) {
    //내부 저장 데이터
    private val loginPrefs : SharedPreferences = context.getSharedPreferences("login",0)
    private val userPrefs : SharedPreferences = context.getSharedPreferences("user",0)
    private val registerPrefs : SharedPreferences = context.getSharedPreferences("register",0)
    //로그인 관련 내부 저장소 저장 및 반환
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
    //회원가입 관련 내부 저장소 저장 및 반환
    fun registerGetString(key : String,value:String):String
        =registerPrefs.getString(key,value).toString()

    fun registerSetString(key : String,str:String){
        registerPrefs.edit().putString(key,str).apply()
    }
    //회원가입 관련 내부 저장소 초기화
    fun registerInit(){
        registerPrefs.edit().clear().apply()
    }
    //로그인한 유저 관련 내부 저장소 저장 및 반환
    fun userGetString(key: String,value: String):String
        =userPrefs.getString(key,value).toString()
    fun userSetString(key: String, str:String){
        userPrefs.edit().putString(key,str).apply()
    }
    //로그인한 유저 관련 내부 저장소 초기화
    fun userInit(){
        userPrefs.edit().clear().apply()
    }
}
