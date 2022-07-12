package com.example.allyourfavouritefilms.DI.modules

import com.example.allyourfavouritefilms.data.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository() = MainRepository()
}