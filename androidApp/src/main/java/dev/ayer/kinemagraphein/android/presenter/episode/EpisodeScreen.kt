package dev.ayer.kinemagraphein.android.presenter.episode

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ayer.kinemagraphein.android.di.repositoriesModule
import dev.ayer.kinemagraphein.android.di.useCaseModules
import dev.ayer.kinemagraphein.android.di.viewModelModules
import dev.ayer.kinemagraphein.android.entity.media.Episode
import dev.ayer.kinemagraphein.android.presenter.designsystem.Header
import dev.ayer.kinemagraphein.android.presenter.designsystem.text.HtmlText
import dev.ayer.kinemagraphein.android.presenter.theme.KinemaGrapheinTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import org.koin.core.parameter.parametersOf

@Composable
fun EpisodeScreen(
    navController: NavHostController,
    seriesId: String,
    episodeSeason: Int,
    episodeNumber: Int,
    viewModel: EpisodeViewModel = koinViewModel(
        parameters = {
            parametersOf(
                seriesId,
                episodeSeason,
                episodeNumber
            )
        }
    )
) {
    val state = viewModel.uiState.collectAsState()
    val episode = state.value.episode

    if (episode == null) {
        return
    }

    LazyColumn {
        item {
            Header(
                media = episode,
                shouldShowFavoriteIcon = false,
                onNavigateBack = { navController.popBackStack() },
                onFavoriteClick = {}
            )
        }
        item { SeasonAndNumber(episode) }
        item { ReleaseDate(episode) }
        item {
            Spacer(modifier = Modifier.size(16.dp).fillMaxWidth())
            Summary(episode)
        }
    }
}

@Composable
private fun SeasonAndNumber(episode: Episode) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "S${episode.season}E${episode.number}",
        style =  MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun ReleaseDate(episode: Episode) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = episode.releaseDate ?: "",
        style =  MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun Summary(episode: Episode) {
    HtmlText(
        text = episode.summary ?: "",
        color = Color.Unspecified,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
    )
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_3_XL,
    showSystemUi = true
)
@Composable
fun EpisodeScreenPreview() {
    KoinApplication(application = {
        modules(
            viewModelModules,
            repositoriesModule,
            useCaseModules
        )
    }) {
        KinemaGrapheinTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                EpisodeScreen(
                    navController = rememberNavController(),
                    seriesId = "1",
                    episodeNumber = 1,
                    episodeSeason = 1
                )
            }
        }
    }
}
