package com.example.allyourfavouritefilms.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allyourfavouritefilms.App
import com.example.allyourfavouritefilms.domain.Film
import com.example.allyourfavouritefilms.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel: ViewModel() {

    /*//Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    /*private var interactor: Interactor = App.instance.interactor*/
    init {
        App.instance.dagger.inject(this)
        getFilms()
    }*/

    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                showProgressBar.postValue(false)
                /*Executors.newSingleThreadExecutor().execute {
                    filmsListLiveData.postValue(interactor.getFilmsFromDB())
                }*/
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}