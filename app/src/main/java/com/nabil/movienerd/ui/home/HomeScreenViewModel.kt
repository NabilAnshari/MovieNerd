package com.nabil.movienerd.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabil.movienerd.model.FilmOrder
import com.nabil.movienerd.repo.FilmRepository
import com.nabil.movienerd.ui.components.StateUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repository: FilmRepository
) : ViewModel() {
    private val _stateUi: MutableStateFlow<StateUi<List<FilmOrder>>> = MutableStateFlow(StateUi.Loading)
    val stateUi: StateFlow<StateUi<List<FilmOrder>>>
        get() = _stateUi

    fun getAllFilms() {
        viewModelScope.launch {
            repository.getAllFilms()
                .catch {
                    _stateUi.value = StateUi.Error(it.message.toString())
                }
                .collect { filmOrder ->
                    _stateUi.value = StateUi.Success(filmOrder)
                }
        }
    }
}