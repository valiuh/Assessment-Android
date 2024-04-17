package com.midtronics.data.api

import com.midtronics.data.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("name/{countryName}")
    suspend fun getCountryByName(@Path("countryName") countryName: String): List<Country>

}

