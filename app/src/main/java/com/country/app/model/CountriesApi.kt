package com.country.app.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface CountriesApi {

    @GET("5dc1258210cc0c3b8dff959a")
    fun getCountries(@Header("secret-key") SecretKey :String): Single<ArrayList<Countries>>
}