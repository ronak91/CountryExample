package com.country.app.model

import com.country.app.di.DaggerApiComponent
import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountriesService {

    private val BASE_URL = "https://api.jsonbin.io/b/"
    private val SecretKey = "$2b$10$.q4CGx6rP3A07oKWLWIbhO7wbuQhzkDx90973wBiRbCiNjZ9iLxhK"

    @Inject
    lateinit var api: CountriesApi

    init {

        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<ArrayList<Countries>> {
        return api.getCountries(SecretKey)
    }
}