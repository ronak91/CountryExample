package com.country.app.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.country.app.model.Countries


class ListViewModel : ViewModel(){

    val countries = MutableLiveData<ArrayList<Countries>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreash(){
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = arrayListOf(
            Countries("CountryA"),
            Countries("CountryB"),
            Countries("CountryC"),
            Countries("CountryD"),
            Countries("CountryE"),
            Countries("CountryF"),
            Countries("CountryG"),
            Countries("CountryH"),
            Countries("CountryI"),
            Countries("CountryJ"),
            Countries("CountryK"),
            Countries("CountryL")
        )

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
}