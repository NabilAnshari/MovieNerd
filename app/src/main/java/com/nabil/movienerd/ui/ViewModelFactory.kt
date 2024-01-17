package com.nabil.movienerd.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nabil.movienerd.repo.FilmRepository
import com.nabil.movienerd.ui.detail.DetailFilmViewModel
import com.nabil.movienerd.ui.home.HomeScreenViewModel

class ViewModelFactory(private val repository: FilmRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailFilmViewModel::class.java)) {
            return DetailFilmViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}