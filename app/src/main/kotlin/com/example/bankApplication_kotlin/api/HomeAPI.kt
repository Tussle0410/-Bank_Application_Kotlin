package com.example.bankApplication_kotlin.api

import com.example.bankApplication_kotlin.api.model.AddressModel
import com.example.bankApplication_kotlin.api.model.BannerModel
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeAPI {
    @GET("getBanner.php")
    fun getBanner() : Call<List<BannerModel>>

    @FormUrlEncoded
    @POST("getCurMoney.php")
    fun getCurMoney(
        @Field("ID") id : String
    ) : Call<List<AddressModel>>

    @FormUrlEncoded
    @POST("getMyasset.php")
    fun getMyAsset(
        @Field("ID") id : String
    ) : Call<List<AddressModel>>

    companion object{
        fun create() : HomeAPI
            =Retrofit.Builder()
            .baseUrl("http://" + PreferenceApplication.ServerIP + "/bank/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeAPI::class.java)
    }
}