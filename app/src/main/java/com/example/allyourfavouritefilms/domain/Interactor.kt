package com.example.allyourfavouritefilms.domain

import com.example.allyourfavouritefilms.data.*
import com.example.allyourfavouritefilms.data.Entity.TmdbResults
import com.example.allyourfavouritefilms.data.preferences.PreferenceProvider
import com.example.allyourfavouritefilms.utils.Converter
import com.example.allyourfavouritefilms.viewModel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {

    fun getFilmsFromApi( page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {

                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {

                callback.onFailure()
            }
        })
    }


    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}