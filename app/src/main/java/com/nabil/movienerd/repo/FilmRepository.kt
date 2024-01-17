package com.nabil.movienerd.repo

import com.nabil.movienerd.model.FilmDataSource
import com.nabil.movienerd.model.FilmOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FilmRepository {

    private val filmOrder = mutableListOf<FilmOrder>()

    init {
        if (filmOrder.isEmpty()) {
            FilmDataSource.dummyFilms.forEach {
                filmOrder.add(FilmOrder(it))
            }
        }
    }

    fun getAllFilms(): Flow<List<FilmOrder>> {
        return flowOf(filmOrder)
    }

    fun getFilmOrderId(filmId: Long): FilmOrder {
        return filmOrder.first {
            it.film.id == filmId
        }
    }

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(): FilmRepository =
            instance ?: synchronized(this) {
                FilmRepository().apply {
                    instance = this
                }
            }
    }
}