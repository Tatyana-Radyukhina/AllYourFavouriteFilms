package com.example.allyourfavouritefilms.DI.modules

import com.example.allyourfavouritefilms.data.MainRepository
import com.example.allyourfavouritefilms.data.TmdbApi
import com.example.allyourfavouritefilms.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}