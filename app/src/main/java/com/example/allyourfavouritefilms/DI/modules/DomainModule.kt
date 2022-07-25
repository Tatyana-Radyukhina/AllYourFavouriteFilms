package com.example.allyourfavouritefilms.DI.modules

import android.content.Context
import com.example.allyourfavouritefilms.data.MainRepository
import com.example.allyourfavouritefilms.data.PreferenceProvider
import com.example.allyourfavouritefilms.data.TmdbApi
import com.example.allyourfavouritefilms.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {

    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    //Создаем экземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}