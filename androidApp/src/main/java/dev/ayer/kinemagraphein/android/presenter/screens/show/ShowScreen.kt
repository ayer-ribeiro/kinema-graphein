package dev.ayer.kinemagraphein.android.presenter.screens.show

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.ayer.kinemagraphein.android.presenter.designsystem.Header
import dev.ayer.kinemagraphein.android.presenter.designsystem.LoadingProgress
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.Button
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.ButtonState
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.ButtonStyle
import dev.ayer.kinemagraphein.android.presenter.designsystem.text.HtmlText
import dev.ayer.kinemagraphein.android.presenter.navigation.navigateSingleTopEpisode
import dev.ayer.kinemagraphein.android.presenter.screens.show.tooling.ShowScreenStatePreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.theme.Grey300
import dev.ayer.kinemagraphein.android.presenter.theme.Grey500
import dev.ayer.kinemagraphein.android.presenter.theme.QuantumTheme
import kotlinx.collections.immutable.ImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ShowScreen(
    navController: NavHostController,
    showId: Long,
    viewModel: ShowScreenViewModel = koinViewModel(parameters = { parametersOf(showId) })
) {
    val screenState by viewModel.uiState.collectAsState()

    val show = remember(screenState) { screenState.showScreenData }
    val seasons = remember(screenState) { screenState.seasons }
    val currentSeason = remember(screenState) { screenState.currentSeason }
    val isLoading = remember(screenState) { screenState.isLoading }

    val onBackClick: () -> Unit = remember(Unit) {
        return@remember { navController.popBackStack() }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is ShowEvents.Navigation.NavigateToEpisodeDetails -> {
                    navController.navigateSingleTopEpisode(
                        showId = showId,
                        season = event.episode.season,
                        number = event.episode.number
                    )
                }
            }
        }
    }

    if (isLoading) {
        LoadingProgress()
        return
    }

    if (show == null) {
        return
    }

    val onEpisodeClick: (ShowScreenEpisodeData) -> Unit = remember(screenState) {
        { episode ->
            viewModel.onSelect(episode)
        }
    }

    val onSelectSeason: (ShowScreenSeasonData) -> Unit = remember(screenState) {
        { season ->
            viewModel.onSelect(season)
        }
    }

    val onFavoriteClick: () -> Unit = remember(screenState) {
        {
            viewModel.onFavoriteClick(show)
        }
    }

    ShowScreenContent(
        showData = show,
        seasonsData = seasons,
        currentSeason = currentSeason!!,
        onBackClick = onBackClick,
        onEpisodeClick = onEpisodeClick,
        onSelectSeason = onSelectSeason,
        onFavoriteClick = onFavoriteClick
    )
}

@Composable
private fun ShowScreenContent(
    currentSeason: ShowScreenSeasonData,
    showData: ShowScreenData,
    seasonsData: ImmutableList<ShowScreenSeasonData>,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onEpisodeClick: (ShowScreenEpisodeData) -> Unit,
    onSelectSeason: (ShowScreenSeasonData) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            Header(
                title = showData.name,
                imageUrl = showData.imageUrl,
                isFavorite = showData.isFavorite,
                onFavoriteClick = onFavoriteClick,
                onNavigateBack = onBackClick,
            )
        }
        item {
            Schedule(
                time = showData.timeSchedule,
                weekdays = showData.weekdays
            )
        }
        item {
            Summary(showData.summary)
            Spacer(modifier = Modifier.size(24.dp))
        }
        item {
            SeasonsButton(
                currentSeason = currentSeason,
                seasons = seasonsData,
                onSelectSeason = onSelectSeason
            )
            Spacer(modifier = Modifier.size(24.dp))
        }

        items(currentSeason.episodes) { item ->
            val onEpisodeItemClick: (ShowScreenEpisodeData) -> Unit = remember(item) {
                { episode ->
                    onEpisodeClick(episode)
                }
            }
            EpisodeItem(
                episode = item,
                onClickEpisode = onEpisodeItemClick
            )
        }

        item {
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun SeasonsButton(
    currentSeason: ShowScreenSeasonData,
    seasons: ImmutableList<ShowScreenSeasonData>,
    onSelectSeason: (ShowScreenSeasonData) -> Unit,
) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
        items(seasons) { season ->
            val seasonNumber = remember(season) { season.number }
            val onSelectItemSeason = remember(season) { { onSelectSeason(season) } }
            val isSelected = remember(key1 = currentSeason, key2 = season) {
                currentSeason == season
            }
            Button(
                text = "Season $seasonNumber",
                style = ButtonStyle.Text,
                state = if (isSelected) ButtonState.Selected else ButtonState.Enabled,
                onClick = onSelectItemSeason
            )
        }
    }
}

@Composable
private fun EpisodeItem(
    episode: ShowScreenEpisodeData,
    onClickEpisode: (ShowScreenEpisodeData) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickEpisode(episode) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EpisodeImage(episode)
            EpisodeInfo(episode)
        }
    }
}

@Composable
private fun EpisodeInfo(episode: ShowScreenEpisodeData) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = episode.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
            Text(
                episode.releaseDate ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        val summary = episode.summary
        if (summary.isNullOrBlank()) {
            return
        }

        HtmlText(
            text = summary,
            maxLines = 2,
            minLines = 2,
            color = Grey500,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun EpisodeImage(
    episode: ShowScreenEpisodeData,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val model = remember(episode) {
        ImageRequest.Builder(context)
            .data(episode.imageUrl)
            .crossfade(true)
            .build()
    }
    val placeholderPainter = painterResource(
        id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder
    )
    val episodeNumberString = remember(episode) { episode.number.toString() }

    Card {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = model,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = placeholderPainter,
                error = placeholderPainter,
                modifier = modifier
                    .background(Color(0xFF2a292a))
                    .size(height = 64.dp, width = Dp.Unspecified)
                    .aspectRatio(16f / 9f)
                    .fillMaxSize()

            )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(4.dp, 4.dp, 0.dp, 0.dp)
            ) {
                Text(
                    modifier = modifier.padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    ),
                    text = episodeNumberString,
                    style = MaterialTheme.typography.labelSmall
                )
            }

        }
    }
}

@Composable
private fun Summary(summary: String?) {
    HtmlText(
        text = summary ?: "",
        color = Grey300,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
private fun Schedule(
    time: String,
    weekdays: ImmutableList<String>,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        item { Text(weekdays.joinToString(", ")) }
        item { Text(time) }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_3_XL,
    showSystemUi = true
)
@Composable
fun ShowScreenPreview(
    @PreviewParameter(ShowScreenStatePreviewParameterProvider::class)
    state: ShowScreenState
) {
    QuantumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ShowScreenContent(
                showData = state.showScreenData!!,
                seasonsData = state.seasons,
                currentSeason = state.seasons[0],
                onBackClick = {},
                onEpisodeClick = {},
                onSelectSeason = {},
                onFavoriteClick = {}
            )
        }
    }
}
