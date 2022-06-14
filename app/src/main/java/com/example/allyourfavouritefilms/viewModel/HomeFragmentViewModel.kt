package com.example.allyourfavouritefilms.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allyourfavouritefilms.App
import com.example.allyourfavouritefilms.domain.Film
import com.example.allyourfavouritefilms.domain.Interactor

class HomeFragmentViewModel: ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private var interactor: Interactor = App.instance.interactor
    init{

        //TODO init interactor

        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}