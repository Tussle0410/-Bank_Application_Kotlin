package com.example.bankApplication_kotlin.api

import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
//회원가입 관련 API 모음
interface RegisterAPI {
    //회원가입
    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("ID") ID : String,
        @Field("PW") PW : String,
        @Field("Name") Name : String,
        @Field("Gender") Gender : String,
        @Field("Email") Email : String,
        @Field("Birth") Birth : String,
        @Field("Address") Address : String
    ) : Call<String>

    //중복 ID 확인
    @FormUrlEncoded
    @POST("regCheckID.php")
    fun regCheckID(
        @Field("ID") ID : String
    ) : Call<String>

    //중복 Email 확인
    @FormUrlEncoded
    @POST("regCheckEmail.php")
    fun regCheckEmail(
        @Field("Email") Email: String
    ) : Call<String>

    //중복 accountAddress 확인
    @FormUrlEncoded
    @POST("accountAddressCheck.php")
    fun regAccountAddressCheck(
        @Field("address") address : String
    ) : Call<String>

    companion object{
        //retrofit2 client
        fun create() : RegisterAPI
            =Retrofit.Builder()
                    .baseUrl("http://" + PreferenceApplication.ServerIP + "/bank/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RegisterAPI::class.java)
    }
}
