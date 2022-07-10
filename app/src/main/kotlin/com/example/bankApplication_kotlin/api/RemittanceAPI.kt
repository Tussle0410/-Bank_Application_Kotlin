package com.example.bankApplication_kotlin.api

import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RemittanceAPI {
    
    companion object{
        fun create() : RemittanceAPI
        =Retrofit.Builder()
            .baseUrl("http://" + PreferenceApplication.ServerIP + "/bank/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemittanceAPI::class.java)
    }
}