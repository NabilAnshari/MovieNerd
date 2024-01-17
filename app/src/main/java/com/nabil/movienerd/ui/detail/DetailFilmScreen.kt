package com.nabil.movienerd.ui.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nabil.movienerd.R
import com.nabil.movienerd.di.Injection
import com.nabil.movienerd.ui.ViewModelFactory
import com.nabil.movienerd.ui.components.StateUi
import com.nabil.movienerd.ui.theme.MovieNerdTheme

@Composable
fun DetailFilmScreen(
    filmId: Long,
    viewModel: DetailFilmViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = StateUi.Loading).value.let { stateUi ->
        when (stateUi) {
            is StateUi.Loading -> {
                viewModel.getRewardById(filmId)
            }
            is StateUi.Success -> {
                val data = stateUi.data
                DetailContent(
                    data.film.image,
                    data.film.title,
                    data.film.director,
                    data.film.description,
                    onBackClick = navigateBack
                )
            }
            is StateUi.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    director: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(550.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = director,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = stringResource(R.string.desk),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    MovieNerdTheme {
        DetailContent(
            R.drawable.fight_club,
            "Fight Club",
            "David Fincher",
            "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more. dirilis pada tahun 1999 yang diadaptasi dari novel yang juga berjudul Fight Club karya Chuck Palahniuk. Film ini disutradarai oleh David Fincher dan diperankan oleh Edward Norton (tokoh protagonis dalam film ini yang tanpa nama dan selaku narator). Film yang mengisahkan seorang pria yang terjebak dalam kehidupan sebagai masyarakat pekerja \"kerah putih\" yang terlibat klub pertarungan ilegal yang dirintis oleh penjual sabun yang bernama Tyler Durden (Brad Pitt). Mereka terjerumus dalam kisah segitiga dengan seorang wanita bernama Marla Singer (Helena Bonham Carter).",
            onBackClick = {}
        )
    }
}