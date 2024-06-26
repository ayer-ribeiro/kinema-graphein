package dev.ayer.kinemagraphein.android.presenter.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.ayer.kinemagraphein.android.R
import dev.ayer.kinemagraphein.android.presenter.designsystem.LoadingProgress
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.Button
import dev.ayer.kinemagraphein.android.presenter.designsystem.base.ButtonState
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaGridSection
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaRowSection
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCover
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiState
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiStateListPreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiStatePreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.designsystem.text.SectionTitle
import dev.ayer.kinemagraphein.android.presenter.imageresource.FavoriteIllustration
import dev.ayer.kinemagraphein.android.presenter.imageresource.getContentDescription
import dev.ayer.kinemagraphein.android.presenter.navigation.arguments.Argument.Companion.with
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.Destination
import dev.ayer.kinemagraphein.android.presenter.navigation.navigate
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.with
import dev.ayer.kinemagraphein.android.presenter.navigation.params.AppNavParam
import dev.ayer.kinemagraphein.android.presenter.theme.QuantumTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel

typealias SearchActiveStateChanged = HomeActionsIntent.SearchActiveStateChanged
typealias SearchQueryCleared = HomeActionsIntent.SearchQueryCleared
typealias SearchQueryChanged = HomeActionsIntent.SearchQueryChanged
typealias FavoriteClicked = HomeActionsIntent.FavoriteClicked
typealias ExpandFavoriteClicked = HomeActionsIntent.ExpandFavoriteClicked
typealias SearchCalled = HomeActionsIntent.SearchCalled
typealias MediaClicked = HomeActionsIntent.MediaClicked
typealias LoadMoreItems = HomeActionsIntent.LoadMoreItems

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
    val shouldShowFavoriteExpandButton = remember(screenState) { screenState.shouldShowMoreFavoriteButton }
    val allMediaList = remember(screenState) { screenState.allMediaList }
    val isLoadingMoreItems = remember(screenState) { screenState.isLoadingMoreItems }
    val isLoading = remember(screenState) { screenState.isLoading }
    val hasError = remember(screenState) { screenState.hasError }
    // endregion

    // region Events handling
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect {
            when (it) {
                is HomeEvents.Navigation.NavigateToShowDetails -> {
                    Destination
                        .Show(showId = it.mediaId)
                        .with(navController)
                        .navigate()
                }

                HomeEvents.Navigation.NavigateToFavoritesList -> {
                    Destination
                        .FavoriteList
                        .with(navController)
                        .navigate()
                }
            }
        }
    }
    // endregion

    // region Search Bar callbacks
    val onSearchBarContentClick: (MediaItemCoverUiState) -> Unit =
        remember(searchBarState) { { viewModel.onAction(MediaClicked(it)) } }
    val onSearchBarFavoriteIconClick: (MediaItemCoverUiState) -> Unit =
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
        remember(screenState) { { viewModel.onAction(LoadMoreItems) } }

    val onShowClick: (MediaItemCoverUiState) -> Unit =
        remember(screenState) { { viewModel.onAction(MediaClicked(it)) } }

    val onFavoriteClick: (MediaItemCoverUiState) -> Unit =
        remember(screenState) { { viewModel.onAction(FavoriteClicked(it)) } }

    val onExpandFavoritesClick: () -> Unit =
        remember(screenState) { { viewModel.onAction(ExpandFavoriteClicked) } }

    val onLoadMoreButtonClick: () -> Unit =
        remember(screenState) { { viewModel.onAction(LoadMoreItems) } }
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
            shouldShowFavoriteExpandButton = shouldShowFavoriteExpandButton,
            isLoadingMoreItems = isLoadingMoreItems,
            onShowClick = onShowClick,
            onFavoriteClick = onFavoriteClick,
            onLoadMoreButtonClick = onLoadMoreButtonClick,
            onExpandFavoritesClick = onExpandFavoritesClick
        )
    }
}

@Composable
private fun ContentState(
    recent: ImmutableList<MediaItemCoverUiState>,
    favorites: ImmutableList<MediaItemCoverUiState>,
    shouldShowFavoriteExpandButton: Boolean,
    allMediaList: ImmutableList<MediaItemCoverUiState>,
    isLoadingMoreItems: Boolean,
    onShowClick: (MediaItemCoverUiState) -> Unit,
    onFavoriteClick: (MediaItemCoverUiState) -> Unit,
    onExpandFavoritesClick: () -> Unit,
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

        FavoriteSection(
            favorites = favorites,
            shouldShowExpandButton = shouldShowFavoriteExpandButton,
            onShowClick = onShowClick,
            onFavoriteClick = onFavoriteClick,
            onExpandFavoritesClick = onExpandFavoritesClick,
        )

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
    allMediaList: ImmutableList<MediaItemCoverUiState>,
    onShowClick: (MediaItemCoverUiState) -> Unit = {},
    onFavoriteClick: (MediaItemCoverUiState) -> Unit = {}
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
    favorites: ImmutableList<MediaItemCoverUiState>,
    shouldShowExpandButton: Boolean,
    onShowClick: (MediaItemCoverUiState) -> Unit,
    onFavoriteClick: (MediaItemCoverUiState) -> Unit,
    onExpandFavoritesClick: () -> Unit
) {
    stickyHeader {
        SectionTitle(
            text = "Favorites",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        )
    }
    if (favorites.isEmpty()) {
        item {
            val context = LocalContext.current
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val painter = painterResource(id = FavoriteIllustration.drawableResId)
                val contentDescription = context.getContentDescription(FavoriteIllustration)
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    modifier = Modifier.width(width = 140.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = context
                        .getString(R.string.home_favorite_empty_state_title),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    text = context.getString(R.string.home_favorite_empty_state_message)
                )
            }
        }
    } else {
        item {
            MediaRowSection(
                mediaItems = favorites,
                onContentClick = onShowClick,
                onFavoriteIconClick = onFavoriteClick
            )
        }
    }

    if (shouldShowExpandButton) {
        item {
            Button(
                text = "Show all favorites",
                onClick = onExpandFavoritesClick,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 48.dp)
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("FunctionName")
private fun LazyListScope.RecentSection(
    recent: ImmutableList<MediaItemCoverUiState>,
    onShowClick: (MediaItemCoverUiState) -> Unit = {},
    onFavoriteClick: (MediaItemCoverUiState) -> Unit = {}
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
    onContentClick: (MediaItemCoverUiState) -> Unit,
    onFavoriteIconClick: (MediaItemCoverUiState) -> Unit,
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
    @PreviewParameter(MediaItemCoverUiStatePreviewParameterProvider::class) showData: List<MediaItemCoverUiState>
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
                onLoadMoreButtonClick = {},
                onExpandFavoritesClick = {},
                shouldShowFavoriteExpandButton = true
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
    @PreviewParameter(MediaItemCoverUiStateListPreviewParameterProvider::class) showData: ImmutableList<MediaItemCoverUiState>
) {
    QuantumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ContentState(
                recent = emptyList<MediaItemCoverUiState>().toImmutableList(),
                favorites = emptyList<MediaItemCoverUiState>().toImmutableList(),
                allMediaList = showData.subList(0, 6).toImmutableList(),
                isLoadingMoreItems = true,
                shouldShowFavoriteExpandButton = false,
                onShowClick = {},
                onFavoriteClick = {},
                onExpandFavoritesClick = {},
                onLoadMoreButtonClick = {}
            )
        }
    }
}