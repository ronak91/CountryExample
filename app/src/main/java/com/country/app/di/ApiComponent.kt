package com.country.app.di

import com.country.app.ViewModel.ListViewModel
import com.country.app.model.CountriesService
import dagger.Component


@Component(modules =  [ApiModule::class])
interface  ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)

}