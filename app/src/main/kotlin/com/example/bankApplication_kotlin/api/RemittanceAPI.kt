package com.example.bankApplication_kotlin.api

import com.example.bankApplication_kotlin.api.model.AddressModel
import com.example.bankApplication_kotlin.api.model.UserModel
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemittanceAPI {
    @FormUrlEncoded
    @POST("remittance_CheckAddress.php")
    fun remittanceAddressCheck(
        @Field("address") address : String
    ) : Call<List<AddressModel>>
    @FormUrlEncoded
    @POST("remittance_emailCheck.php")
    fun remittanceEmailCheck(
        @Field("email") email : String,
        @Field("name") name : String
    ) : Call<List<AddressModel>>
    @FormUrlEncoded
    @POST("remittance.php")
    fun remittance(
        @Field("address") address : String,
        @Field("receiveAddress") receiveAddress : String,
        @Field("ID") ID : String,
        @Field("name") name : String,
        @Field("receiveName") receiverName : String,
        @Field("money") money : String,
        @Field("pw") pw : String
    ) : Call<String>
    companion object{
        fun create() : RemittanceAPI
        =Retrofit.Builder()
            .baseUrl("http://" + PreferenceApplication.ServerIP + "/bank/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemittanceAPI::class.java)
    }
}