package com.example.bankApplication_kotlin.api.model

import com.google.gson.annotations.SerializedName

data class BannerModel (
    @SerializedName("bannerRoute") val bannerRoute : String,
    @SerializedName("kinds") val kinds : String
    )