package dev.ayer.kinemagraphein.android.presenter.series

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.ayer.kinemagraphein.android.di.repositoriesModule
import dev.ayer.kinemagraphein.android.di.useCaseModules
import dev.ayer.kinemagraphein.android.di.viewModelModules
import dev.ayer.kinemagraphein.android.entity.media.Episode
import dev.ayer.kinemagraphein.android.entity.media.Show
import dev.ayer.kinemagraphein.android.presenter.designsystem.Header
import dev.ayer.kinemagraphein.android.presenter.designsystem.LoadingProgress
import dev.ayer.kinemagraphein.android.presenter.designsystem.text.HtmlText
import dev.ayer.kinemagraphein.android.presenter.designsystem.text.SectionTitle
import dev.ayer.kinemagraphein.android.presenter.navigation.navigateSingleTopEpisode
import dev.ayer.kinemagraphein.android.presenter.theme.Grey300
import dev.ayer.kinemagraphein.android.presenter.theme.Grey500
import dev.ayer.kinemagraphein.android.presenter.theme.KinemaGrapheinTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowScreen(
    navController: NavHostController,
    seriesId: String,
    viewModel: ShowScreenViewModel = koinViewModel(parameters = { parametersOf(seriesId) })
) {
    val screenState by viewModel.uiState.collectAsState()
    val show = screenState.show
    val isLoading = screenState.isLoading
    val pagerState = rememberPagerState(pageCount = { show?.seasons?.size ?: 0 })

    if (isLoading) {
        LoadingProgress()
        return
    }

    if (show == null) {
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Header(
                media = show,
                onFavoriteClick = {},
                onNavigateBack = { navController.popBackStack() },
            )
        }
        item { Schedule(show) }
        item { Summary(show) }
        item { Spacer(modifier = Modifier.size(24.dp)) }
        item { PagerIndicator(pagerState) }
        item {
            SeasonsPager(pagerState, show, onClickEpisode = { episode ->
                navController.navigateSingleTopEpisode(
                    seriesId = seriesId,
                    season = episode.season,
                    number = episode.number
                )
            })
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun SeasonsPager(
    pagerState: PagerState,
    show: Show,
    onClickEpisode: (Episode) -> Unit
) {
    HorizontalPager(state = pagerState) { page ->
        val season = show.seasons[page]
        val episodes = season.episodes

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SeasonTitle(page)
            episodes.forEach { episode ->
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
        }
    }
}

@Composable
private fun EpisodeInfo(episode: Episode) {
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

        if (episode.summary.isNullOrBlank()) {
            return
        }

        HtmlText(
            text = episode.summary,
            maxLines = 2,
            minLines = 2,
            color = Grey500,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun EpisodeImage(
    episode: Episode,
    modifier: Modifier = Modifier
) {
    Card {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(episode.mediumImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
                error = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
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
                    modifier = modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    text = episode.number.toString(), style = MaterialTheme.typography.labelSmall
                )
            }

        }
    }
}

@Composable
private fun SeasonTitle(page: Int) {
    SectionTitle(
        text = "Season ${page + 1}",
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerIndicator(pagerState: PagerState) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }
    }
}

@Composable
private fun Summary(show: Show) {
    HtmlText(
        text = show.summary ?: "",
        color = Grey300,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
private fun Schedule(show: Show) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        item { Text(show.schedule.weekdays.joinToString(", ")) }
        item { Text(show.schedule.time) }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_3_XL,
    showSystemUi = true
)
@Composable
fun SeriesScreenPreview() {
    KoinApplication(application = {
        modules(
            viewModelModules,
            repositoriesModule,
            useCaseModules
        )
    }) {
        KinemaGrapheinTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                ShowScreen(
                    navController = rememberNavController(),
                    seriesId = "1"
                )
            }
        }
    }
}
