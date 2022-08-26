package com.example.allyourfavouritefilms.data

import android.content.ContentValues
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.allyourfavouritefilms.R
import com.example.allyourfavouritefilms.data.dao.FilmDao
import com.example.allyourfavouritefilms.db.DatabaseHelper
import com.example.allyourfavouritefilms.domain.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {
    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<List<Film>> {
        return filmDao.getCachedFilms()
    }
}