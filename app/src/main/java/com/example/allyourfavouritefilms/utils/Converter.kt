package com.example.allyourfavouritefilms.utils

import com.example.allyourfavouritefilms.data.Entity.TmdbFilm
import com.example.allyourfavouritefilms.domain.Film

object Converter {
    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(
                Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
            )
            )
        }
        return result
    }
}