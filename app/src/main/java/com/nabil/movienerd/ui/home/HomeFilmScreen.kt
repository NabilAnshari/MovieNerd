package com.nabil.movienerd.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nabil.movienerd.di.Injection
import com.nabil.movienerd.model.FilmOrder
import com.nabil.movienerd.ui.ViewModelFactory
import com.nabil.movienerd.ui.components.FilmItem
import com.nabil.movienerd.ui.components.StateUi

@Composable
fun HomeFilmScreen(
   modifier: Modifier = Modifier,
   viewModel: HomeScreenViewModel = viewModel(
      factory = ViewModelFactory(Injection.provideRepository())
   ),
   navigateToDetail: (Long) -> Unit
){
   viewModel.stateUi.collectAsState(initial = StateUi.Loading).value.let { stateUi ->
      when (stateUi) {
         is StateUi.Loading -> {
            viewModel.getAllFilms()
         }
         is StateUi.Success -> {
            FilmContent(
               filmOrder = stateUi.data,
               modifier = modifier,
               navigateToDetail = navigateToDetail,
            )
         }
         is StateUi.Error -> {}
      }
   }
}

@Composable
fun FilmContent(
   filmOrder: List<FilmOrder>,
   modifier: Modifier = Modifier,
   navigateToDetail: (Long) -> Unit,
){
   LazyVerticalGrid(
      columns = GridCells.Adaptive(140.dp),
      contentPadding = PaddingValues(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = modifier
   ){
      items(filmOrder){data ->
         FilmItem(
            image = data.film.image,
            title = data.film.title,
            director = data.film.director,
            modifier = Modifier.clickable {
               navigateToDetail(data.film.id)
            }
         )
      }
   }
}