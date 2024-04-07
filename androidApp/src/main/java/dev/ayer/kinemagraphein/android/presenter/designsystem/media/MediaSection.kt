package dev.ayer.kinemagraphein.android.presenter.designsystem.media

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ayer.kinemagraphein.entity.media.ShowBase

@Composable
fun MediaGridSection(
    mediaItems: List<ShowBase>,
    onContentClick: (ShowBase) -> Unit,
    onFavoriteIconClick: (ShowBase) -> Unit
) {
    GridSection(mediaItems) { media ->
        val onContentItemClick = remember(media) {
            { onContentClick(media) }
        }
        val onFavoriteIconItemClick = remember(media) {
            { onFavoriteIconClick(media) }
        }
        MediaItemCover(
            media = media,
            onContentClick = onContentItemClick,
            onFavoriteIconClick = onFavoriteIconItemClick
        )
    }
}

@Composable
fun MediaRowSection(
    mediaItems: List<ShowBase>,
    onContentClick: (ShowBase) -> Unit,
    onFavoriteIconClick: (ShowBase) -> Unit,
) {
    LazyRowSection(mediaItems) { media ->
        val onContentItemClick = remember(media) {
            { onContentClick(media) }
        }
        val onFavoriteIconItemClick = remember(media) {
            { onFavoriteIconClick(media) }
        }
        MediaItemCover(
            media = media,
            modifier = Modifier.width(120.dp),
            onContentClick = onContentItemClick,
            onFavoriteIconClick = onFavoriteIconItemClick
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MediaFlowRowSection(
    mediaItems: List<ShowBase>,
    onContentClick: (ShowBase) -> Unit,
    onFavoriteIconClick: (ShowBase) -> Unit,
) {
    FlowRowSection(mediaItems) { media ->
        val onContentItemClick = remember(media) {
            { onContentClick(media) }
        }
        val onFavoriteIconItemClick = remember(media) {
            { onFavoriteIconClick(media) }
        }
        MediaItemCover(
            media = media,
            modifier = Modifier.weight(3f, fill = true),
            onContentClick = onContentItemClick,
            onFavoriteIconClick = onFavoriteIconItemClick
        )
    }
}

@Composable
fun MediaLazyFlowSection(
    mediaItems: List<ShowBase>,
    onContentClick: (ShowBase) -> Unit,
    onFavoriteIconClick: (ShowBase) -> Unit,
) {
    LazyFlowSection(mediaItems) { media ->
        val onContentItemClick = remember(media) {
            { onContentClick(media) }
        }
        val onFavoriteIconItemClick = remember(media) {
            { onFavoriteIconClick(media) }
        }
        MediaItemCover(
            media = media,
            modifier = Modifier.weight(3f, fill = true),
            onContentClick = onContentItemClick,
            onFavoriteIconClick = onFavoriteIconItemClick
        )
    }
}

@Composable
fun <T> LazyRowSection(items: List<T>, itemContent: @Composable LazyItemScope.(item: T) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items) { item ->
            itemContent(item)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <T> FlowRowSection(items: List<T>, itemContent: @Composable FlowRowScope.(item: T) -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(16.dp),
        maxItemsInEachRow = 3,
    ) {
        items.forEach { item ->
            itemContent(item)
        }
    }
}

@Composable
fun <T> LazyFlowSection(items: List<T>, itemContent: @Composable RowScope.(item: T) -> Unit) {
    LazyColumn {
        items(items.size / 3) { i ->
            Row {
                items.subList(i * 3, i * 3 + 3).forEach { item ->
                    itemContent(item)
                }
            }
        }
    }
}

@Composable
fun <T> GridSection(items: List<T>, itemContent: @Composable LazyGridItemScope.(item: T) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            itemContent(item)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowGridView(rows: Int, columns: Int) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        repeat(rows) {
            val itemModifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
                .weight(1f)

            Box(modifier = itemModifier) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    repeat(columns) { i ->
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        ) {
                        }
                    }
                }
            }
        }
    }
}
