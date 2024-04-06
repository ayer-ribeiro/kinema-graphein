package dev.ayer.kinemagraphein.android.presenter.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.ayer.kinemagraphein.android.presenter.designsystem.LoadingProgress
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.Button
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaGridSection
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaItemCover
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaRowSection
import dev.ayer.kinemagraphein.android.presenter.designsystem.text.SectionTitle
import dev.ayer.kinemagraphein.android.presenter.navigation.navigateSingleTopToSeries
import dev.ayer.kinemagraphein.entity.media.ShowBase
import org.koin.androidx.compose.koinViewModel

typealias SearchActiveStateChanged = HomeActionsIntent.SearchActiveStateChanged
typealias SearchQueryCleared = HomeActionsIntent.SearchQueryCleared
typealias SearchQueryChanged = HomeActionsIntent.SearchQueryChanged
typealias FavoriteClicked = HomeActionsIntent.FavoriteClicked
typealias SearchCalled = HomeActionsIntent.SearchCalled
typealias MediaClicked = HomeActionsIntent.MediaClicked

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val screenState by viewModel.uiState.collectAsState()
    val recent = screenState.recent
    val favorites = screenState.favorites
    val allMediaList = screenState.allMediaList
    val isLoadingMoreItems = screenState.isLoadingMoreItems
    val isLoading = screenState.isLoading

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect {
            when (it) {
                is HomeEvents.Navigation.NavigateToSeriesDetails -> {
                    navController.navigateSingleTopToSeries(
                        it.mediaId
                    )
                }
            }
        }
    }

    Column {
        AppSearchBar(
            screenState = screenState,
            onContentClick = { viewModel.onAction(MediaClicked(it)) },
            onFavoriteIconClick = { viewModel.onAction(FavoriteClicked(it)) },
            onClearIconClick = { viewModel.onAction(SearchQueryCleared) },
            onQueryChange = { viewModel.onAction(SearchQueryChanged(it)) },
            onSearch = { viewModel.onAction(SearchCalled(it)) },
            onActiveStateChange = { viewModel.onAction(SearchActiveStateChanged(it)) }
        )

        if (isLoading) {
            LoadingProgress(
                modifier = Modifier.fillMaxSize(),
            )
            return
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            if (recent.isNotEmpty()) {
                RecentSection(recent, viewModel)
            }

            if (favorites.isNotEmpty()) {
                FavoriteSection(favorites, viewModel)
            }

            if (allMediaList.isNotEmpty()) {
                AllMediaList(allMediaList, viewModel)
            }

            if (allMediaList.isNotEmpty()) {
                LoadMoreButton(isLoadingMoreItems, viewModel)
            }
        }
    }
}

private fun LazyListScope.LoadMoreButton(
    isLoadingMoreItems: Boolean,
    viewModel: HomeViewModel
) {
    item {
        Button(
            text = "Load more",
            loading = isLoadingMoreItems,
            onClick = { viewModel.onAction(HomeActionsIntent.LoadMoreItems) },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("FunctionName")
private fun LazyListScope.AllMediaList(
    allMediaList: List<ShowBase>,
    viewModel: HomeViewModel
) {
    stickyHeader {
        SectionTitle(
            text = "All Shows",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        )
    }
    item {
        Spacer(modifier = Modifier.size(16.dp))
    }
    items(allMediaList.size / 3) { i ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            allMediaList.subList(i * 3, i * 3 + 3).forEach { item ->
                MediaItemCover(
                    media = item,
                    modifier = Modifier.weight(3f, fill = true),
                    onContentClick = { viewModel.onAction(MediaClicked(item)) },
                    onFavoriteIconClick = { viewModel.onAction(FavoriteClicked(item)) }
                )
            }
        }
    }
    item {
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("FunctionName")
private fun LazyListScope.FavoriteSection(
    favorites: List<ShowBase>,
    viewModel: HomeViewModel
) {
    stickyHeader {
        SectionTitle(
            text = "Favorites",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        )
    }
    item {
        MediaRowSection(
            mediaItems = favorites,
            onContentClick = { viewModel.onAction(MediaClicked(it)) },
            onFavoriteIconClick = { viewModel.onAction(FavoriteClicked(it)) }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("FunctionName")
private fun LazyListScope.RecentSection(
    recent: List<ShowBase>,
    viewModel: HomeViewModel
) {
    stickyHeader {
        SectionTitle(
            text = "Recent",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        )
    }
    item {
        MediaRowSection(
            mediaItems = recent,
            onContentClick = { viewModel.onAction(MediaClicked(it)) },
            onFavoriteIconClick = { viewModel.onAction(FavoriteClicked(it)) }
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppSearchBar(
    screenState: HomeScreenState,
    onContentClick: (ShowBase) -> Unit,
    onFavoriteIconClick: (ShowBase) -> Unit,
    onClearIconClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveStateChange: (Boolean) -> Unit,
) {
    val searchResult = screenState.searchResult
    val searchQuery = screenState.searchQuery
    val searchActiveState = screenState.searchActiveState

    SearchBar(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        query = searchQuery,
        trailingIcon = {
            SearchClearIcon(
                onClearClick = onClearIconClick,
                onSearchIconClick = { onActiveStateChange(true) },
                searchActiveState = searchActiveState
            )
        },
        active = searchActiveState,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        onActiveChange = onActiveStateChange,
    ) {
        MediaGridSection(
            mediaItems = searchResult,
            onContentClick = onContentClick,
            onFavoriteIconClick = onFavoriteIconClick
        )
    }
}

@Composable
private fun SearchClearIcon(
    searchActiveState: Boolean,
    modifier: Modifier = Modifier,
    onClearClick: () -> Unit = {},
    onSearchIconClick: () -> Unit = {}
) {
    val callback = if (searchActiveState) onClearClick else onSearchIconClick
    val icon = if (searchActiveState) Icons.Rounded.Close else Icons.Rounded.Search
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(48.dp)
            .clickable(onClick = callback)

    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

//@Preview(
//    showBackground = true,
//    device = Devices.PIXEL_3_XL,
//    showSystemUi = true
//)
//@Composable
//fun HomeScreenPreview() {
//    KoinApplication(application = { modules(appModules) }) {
//        QuantumTheme {
//            Surface(color = MaterialTheme.colorScheme.background) {
//                HomeScreen(navController = rememberNavController())
//            }
//        }
//    }
//}
