package com.country.app.model

import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryService {

    private val BASE_URL = "https://api.jsonbin.io/b/"
    private val SecretKey = "$2b$10$.q4CGx6rP3A07oKWLWIbhO7wbuQhzkDx90973wBiRbCiNjZ9iLxhK"
    private val api: CountriesApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    fun getCountries(): Single<ArrayList<Countries>> {
        return api.getCountries(SecretKey)
    }
}