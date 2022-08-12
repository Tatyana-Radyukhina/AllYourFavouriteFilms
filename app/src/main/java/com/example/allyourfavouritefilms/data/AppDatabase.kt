package com.example.allyourfavouritefilms.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.allyourfavouritefilms.data.dao.FilmDao
import com.example.allyourfavouritefilms.domain.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}