package com.midtronics.data

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: CountryName,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("population") val population: String,
    @SerializedName("area") val area: String,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String,
)

data class CountryName(
    @SerializedName("common") val common: String,
    @SerializedName("official") val official: String,
)