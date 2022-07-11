package com.example.bankApplication_kotlin.api.model

import com.google.gson.annotations.SerializedName

data class AddressModel(
    @SerializedName("ID") val ID : String,
    @SerializedName("address") val address : String,
    @SerializedName("productionNo")val productionNo : String,
    @SerializedName("money") val money : String,
    @SerializedName("remittanceLimit") val remittanceLimit : String,
    @SerializedName("currentRemittance") val currentRemittance : String,
    @SerializedName("addressName") val addressName : String,
    @SerializedName("mainAddressCheck")val mainAddressCheck : String,
    @SerializedName("kinds") val kinds : String,
    @SerializedName("Name") val Name : String
)
