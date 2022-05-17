package com.example.bankApplication_kotlin.api

import com.example.bankApplication_kotlin.api.model.UserModel
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
//계정 찾기 관련 API
interface FoundAPI {
    //ID 찾기
    @FormUrlEncoded
    @POST("foundID.php")
    fun FoundID(
        @Field("name") name : String,
        @Field("birth") birth : String
    ) : Call<UserModel>

    //PW 찾기
    @FormUrlEncoded
    @POST("foundPW.php")
    fun FoundPW(
        @Field("ID") ID : String,
        @Field("Email") Email : String
    ) : Call<List<UserModel>>

    companion object{
        fun create() : FoundAPI
            =Retrofit.Builder()
                    .baseUrl("http://" + PreferenceApplication.ServerIP + "/bank/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FoundAPI::class.java)
    }
}