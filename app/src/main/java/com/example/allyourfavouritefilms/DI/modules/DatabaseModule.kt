package com.example.allyourfavouritefilms.DI.modules

import android.content.Context
import androidx.room.Room
import com.example.allyourfavouritefilms.data.AppDatabase
import com.example.allyourfavouritefilms.data.MainRepository
import com.example.allyourfavouritefilms.data.dao.FilmDao
import com.example.allyourfavouritefilms.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =  Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "film_db"
    ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}