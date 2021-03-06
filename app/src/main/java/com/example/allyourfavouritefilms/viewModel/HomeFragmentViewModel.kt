package com.example.allyourfavouritefilms.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allyourfavouritefilms.App
import com.example.allyourfavouritefilms.domain.Film
import com.example.allyourfavouritefilms.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel: ViewModel() {

    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    /*private var interactor: Interactor = App.instance.interactor*/
    init {
        App.instance.dagger.inject(this)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }

            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}