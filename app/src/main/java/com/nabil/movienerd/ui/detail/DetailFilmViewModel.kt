package com.nabil.movienerd.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabil.movienerd.model.FilmOrder
import com.nabil.movienerd.repo.FilmRepository
import com.nabil.movienerd.ui.components.StateUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFilmViewModel(
    private val repository: FilmRepository
) : ViewModel() {
    private val _stateUi: MutableStateFlow<StateUi<FilmOrder>> =
        MutableStateFlow(StateUi.Loading)
    val uiState: StateFlow<StateUi<FilmOrder>>
        get() = _stateUi

    fun getRewardById(rewardId: Long) {
        viewModelScope.launch {
            _stateUi.value = StateUi.Loading
            _stateUi.value =StateUi.Success(repository.getFilmOrderId(rewardId))
        }
    }
}