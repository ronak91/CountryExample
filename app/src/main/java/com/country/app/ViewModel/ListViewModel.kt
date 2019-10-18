package com.country.app.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.country.app.model.Country


class ListViewModel : ViewModel(){

    val country = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreash(){
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(
            Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF"),
            Country("CountryG"),
            Country("CountryH"),
            Country("CountryI"),
            Country("CountryJ"),
            Country("CountryK"),
            Country("CountryL")
        )

        countryLoadError.value = false
        loading.value = false
        country.value = mockData
    }
}