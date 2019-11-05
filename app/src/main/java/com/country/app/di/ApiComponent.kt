package com.country.app.di

import com.country.app.model.CountryService
import dagger.Component


@Component(modules =  [ApiModule::class])
interface  ApiComponent {

    fun inject(service: CountryService)

}