package com.country.app.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/CountriesV2.json")
    fun getCountries(): Single<ArrayList<Countries>>
}