package com.country.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.country.app.ViewModel.ListViewModel
import com.country.app.model.Countries
import com.country.app.model.CountriesService
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class ListViewModelTest {

    @get: Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countriesService: CountriesService

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<ArrayList<Countries>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCountriesSuccess(){
            val country = Countries("CountryName", "capital","url")
            val countryList = arrayListOf<Countries>(country)
            testSingle = Single.just(countryList)

            `when`(countriesService.getCountries()).thenReturn(testSingle)
            listViewModel.refreash()

            Assert.assertEquals(1, listViewModel.countries.value?.size)
            Assert.assertEquals(false,listViewModel.countryLoadError.value)
            Assert.assertEquals(false,listViewModel.loading.value)
    }

    @Test
    fun getCountriesFail(){
        testSingle = Single.error(Throwable())

        `when`(countriesService.getCountries()).thenReturn(testSingle)

        listViewModel.refreash()

        Assert.assertEquals(true,listViewModel.countryLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)
    }

    @Before
    fun setUpRxSchedulers(){
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }

        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler: Scheduler? -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
    }
}