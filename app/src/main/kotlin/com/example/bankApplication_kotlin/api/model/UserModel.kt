package com.example.bankApplication_kotlin.api.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("ID") val ID : String,
    @SerializedName("PW") val PW : String,
    @SerializedName("Name") val Name : String,
    @SerializedName("Gender") val Gender : String,
    @SerializedName("Email") val Email : String,
    @SerializedName("Birth") val Birth : String,
)
