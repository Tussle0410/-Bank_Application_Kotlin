package com.example.bankApplication_kotlin.api.model

import com.google.gson.annotations.SerializedName

data class HistoryModel(
    @SerializedName("sendAddress") val sendAddress : String,
    @SerializedName("receiveAddress") val receiveAddress : String,
    @SerializedName("sendName") val sendName : String,
    @SerializedName("receiveName") val receiveName : String,
    @SerializedName("money") val money : String,
    @SerializedName("transactionDate") val transactionDate : String
)
