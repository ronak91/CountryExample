package com.country.app.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.country.app.di.DaggerApiComponent
import com.country.app.model.Countries
import com.country.app.model.CountriesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ListViewModel : ViewModel(){

    @Inject
    lateinit var countriesService: CountriesService

    init {
        DaggerApiComponent.create().inject(this)
    }


    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<ArrayList<Countries>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreash(){
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value  = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ArrayList<Countries>>(){
                    override fun onSuccess(value: ArrayList<Countries>?) {
                        countries.value = value
                        countryLoadError.value = false
                        loading.value = false
                    }
                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }
}