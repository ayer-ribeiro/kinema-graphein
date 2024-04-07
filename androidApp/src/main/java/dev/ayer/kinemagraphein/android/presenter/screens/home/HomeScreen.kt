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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.ayer.kinemagraphein.android.presenter.designsystem.LoadingProgress
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.Button
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.ButtonState
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaGridSection
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaItemCover
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaRowSection
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.ShowBaseListPreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.designsystem.text.SectionTitle
import dev.ayer.kinemagraphein.android.presenter.navigation.navigateSingleTopToSeries
import dev.ayer.kinemagraphein.android.presenter.theme.QuantumTheme
import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
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
    // region Initializations
    val screenState by viewModel.uiState.collectAsState()
    val searchBarState by viewModel.uiSearchBarState.collectAsState()
    val recent = remember(screenState) { screenState.recent }
    val favorites = remember(screenState) { screenState.favorites }
    val allMediaList = remember(screenState) { screenState.allMediaList }
    val isLoadingMoreItems = remember(screenState) { screenState.isLoadingMoreItems }
    val isLoading = remember(screenState) { screenState.isLoading }
    val hasError = remember(screenState) { screenState.hasError }
    // endregion

    // region Events handling
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
    // endregion

    // region Search Bar callbacks
    val onSearchBarContentClick: (ShowBase) -> Unit =
        remember(searchBarState) { { viewModel.onAction(MediaClicked(it)) } }
    val onSearchBarFavoriteIconClick: (ShowBase) -> Unit =
        remember(searchBarState) { { viewModel.onAction(FavoriteClicked(it)) } }
    val onSearchBarClearIconClick: () -> Unit =
        remember(searchBarState) { { viewModel.onAction(SearchQueryCleared) } }
    val onSearchBarQueryChange: (String) -> Unit =
        remember(searchBarState) { { viewModel.onAction(SearchQueryChanged(it)) } }
    val onSearchBarSearch: (String) -> Unit =
        remember(searchBarState) { { viewModel.onAction(SearchCalled(it)) } }
    val onSearchBarActiveStateChange: (Boolean) -> Unit =
        remember(searchBarState) { { viewModel.onAction(SearchActiveStateChanged(it)) } }
    // endregion

    // region Screen callbacks
    val onErrorTryAgainButtonClick: () -> Unit =
        remember(screenState) { { viewModel.onAction(HomeActionsIntent.LoadMoreItems) } }

    val onShowClick: (ShowBase) -> Unit =
        remember(screenState) { { viewModel.onAction(MediaClicked(it)) } }
    val onFavoriteClick: (ShowBase) -> Unit =
        remember(screenState) { { viewModel.onAction(FavoriteClicked(it)) } }

    val onLoadMoreButtonClick: () -> Unit =
        remember(screenState) { { viewModel.onAction(HomeActionsIntent.LoadMoreItems) } }
    // endregion

    Column {
        AppSearchBar(
            state = searchBarState,
            onContentClick = onSearchBarContentClick,
            onFavoriteIconClick = onSearchBarFavoriteIconClick,
            onClearIconClick = onSearchBarClearIconClick,
            onQueryChange = onSearchBarQueryChange,
            onSearch = onSearchBarSearch,
            onActiveStateChange = onSearchBarActiveStateChange
        )

        if (isLoading) {
            LoadingProgress(modifier = Modifier.fillMaxSize())
            return
        }

        if (hasError) {
            ErrorState(onTryAgain = onErrorTryAgainButtonClick)
            return
        }

        ContentState(
            recent = recent,
            favorites = favorites,
            allMediaList = allMediaList,
            isLoadingMoreItems = isLoadingMoreItems,
            onShowClick = onShowClick,
            onFavoriteClick = onFavoriteClick,
            onLoadMoreButtonClick = onLoadMoreButtonClick
        )
    }
}

@Composable
private fun ContentState(
    recent: ImmutableList<ShowBase>,
    favorites: ImmutableList<ShowBase>,
    allMediaList: ImmutableList<ShowBase>,
    isLoadingMoreItems: Boolean,
    onShowClick: (ShowBase) -> Unit,
    onFavoriteClick: (ShowBase) -> Unit,
    onLoadMoreButtonClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        if (recent.isNotEmpty()) {
            RecentSection(
                recent = recent,
                onShowClick = onShowClick,
                onFavoriteClick = onFavoriteClick
            )
        }

        if (favorites.isNotEmpty()) {
            FavoriteSection(
                favorites = favorites,
                onShowClick = onShowClick,
                onFavoriteClick = onFavoriteClick
            )
        }

        if (allMediaList.isNotEmpty()) {
            AllMediaList(
                allMediaList = allMediaList,
                onShowClick = onShowClick,
                onFavoriteClick = onFavoriteClick
            )
        }

        if (allMediaList.isNotEmpty()) {
            LoadMoreButton(
                isLoadingMoreItems = isLoadingMoreItems,
                onClick = onLoadMoreButtonClick
            )
        }
    }
}

@Composable
private fun ErrorState(
    onTryAgain: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Something wrong happens")
        Button(text = "try again", onClick = onTryAgain)
    }
}

private fun LazyListScope.LoadMoreButton(
    isLoadingMoreItems: Boolean,
    onClick: () -> Unit,
) {
    item {
        Button(
            text = "Load more",
            state = if (isLoadingMoreItems) ButtonState.Loading else ButtonState.Enabled,
            onClick = onClick,
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
    onShowClick: (ShowBase) -> Unit = {},
    onFavoriteClick: (ShowBase) -> Unit = {}
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

                val onShowItemClick = remember(item) { { onShowClick(item) } }
                val onFavoriteItemClick = remember(item) { { onFavoriteClick(item) } }
                MediaItemCover(
                    media = item,
                    modifier = Modifier.weight(3f, fill = true),
                    onContentClick = onShowItemClick,
                    onFavoriteIconClick = onFavoriteItemClick
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
    onShowClick: (ShowBase) -> Unit = {},
    onFavoriteClick: (ShowBase) -> Unit = {}
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
            onContentClick = onShowClick,
            onFavoriteIconClick = onFavoriteClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("FunctionName")
private fun LazyListScope.RecentSection(
    recent: List<ShowBase>,
    onShowClick: (ShowBase) -> Unit = {},
    onFavoriteClick: (ShowBase) -> Unit = {}
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
            onContentClick = onShowClick,
            onFavoriteIconClick = onFavoriteClick
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppSearchBar(
    state: SearchBarState,
    onContentClick: (ShowBase) -> Unit,
    onFavoriteIconClick: (ShowBase) -> Unit,
    onClearIconClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveStateChange: (Boolean) -> Unit,
) {
    val searchResult = remember(state) { state.searchResult }
    val searchQuery = remember(state) { state.searchQuery }
    val searchActiveState = remember(state) { state.searchActiveState }

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

@Preview(
    showBackground = true,
    device = Devices.PIXEL_3_XL,
    showSystemUi = true
)
@Composable
fun ContentStatePreview(
    @PreviewParameter(ShowBaseListPreviewParameterProvider::class) showData: List<ShowBase>
) {
    QuantumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ContentState(
                recent = showData.subList(0, 2).toImmutableList(),
                favorites = showData.subList(1, 3).toImmutableList(),
                allMediaList = showData.toImmutableList(),
                isLoadingMoreItems = false,
                onShowClick = {},
                onFavoriteClick = {},
                onLoadMoreButtonClick = {}
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
    device = Devices.PIXEL_3_XL,
    showSystemUi = true,
)
@Composable
fun ContentStatePreview2(
    @PreviewParameter(ShowBaseListPreviewParameterProvider::class) showData: List<ShowBase>
) {
    QuantumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ContentState(
                recent = emptyList<ShowBase>().toImmutableList(),
                favorites = emptyList<ShowBase>().toImmutableList(),
                allMediaList = showData.subList(0, 6).toImmutableList(),
                isLoadingMoreItems = true,
                onShowClick = {},
                onFavoriteClick = {},
                onLoadMoreButtonClick = {}
            )
        }
    }
}