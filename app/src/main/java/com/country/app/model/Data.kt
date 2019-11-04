package com.country.app.model

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("name")
    val countryName: String?,
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("flagPNG")
    val flag: String?
)