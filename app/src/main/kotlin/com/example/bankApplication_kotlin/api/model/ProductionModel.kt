package com.example.bankApplication_kotlin.api.model

import com.google.gson.annotations.SerializedName

data class ProductionModel(
    @SerializedName("productionName") val productionName : String,
    @SerializedName("kinds") val kinds : String,
    @SerializedName("productionNo") val productionNo : String,
    @SerializedName("interestRate") val interestRate : String,
    @SerializedName("description") val description : String,
    @SerializedName("interestCycle") val interestCycle : String
)
