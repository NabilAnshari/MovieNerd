package com.nabil.movienerd.di

import com.nabil.movienerd.repo.FilmRepository

object Injection {
    fun provideRepository(): FilmRepository {
        return FilmRepository.getInstance()
    }
}