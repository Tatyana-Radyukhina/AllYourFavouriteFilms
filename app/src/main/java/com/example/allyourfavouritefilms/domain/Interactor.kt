package com.example.allyourfavouritefilms.domain

import com.example.allyourfavouritefilms.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}