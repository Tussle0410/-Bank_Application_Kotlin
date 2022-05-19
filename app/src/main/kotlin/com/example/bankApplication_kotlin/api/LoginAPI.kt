package com.example.bankApplication_kotlin.api

import com.example.bankApplication_kotlin.api.model.UserModel
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("ID") ID : String,
        @Field("PW") PW : String
    ) : Call<List<UserModel>>

    companion object{
        fun create() : LoginAPI
            = Retrofit.Builder()
                .baseUrl("http://" + PreferenceApplication.ServerIP + "/bank/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginAPI::class.java)
    }
}