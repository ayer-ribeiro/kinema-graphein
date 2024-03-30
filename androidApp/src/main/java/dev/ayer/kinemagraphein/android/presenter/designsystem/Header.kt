package dev.ayer.kinemagraphein.android.presenter.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import dev.ayer.kinemagraphein.android.presenter.designsystem.favorite.FavoriteIcon
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaPreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.theme.QuantumTheme
import dev.ayer.kinemagraphein.entity.media.Coverable
import dev.ayer.kinemagraphein.entity.media.Favoritable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Header(
    data: Coverable,
    favoriteData: Favoritable,
    modifier: Modifier = Modifier,
    shouldShowFavoriteIcon: Boolean = true,
    onNavigateBack: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.originalImageUrl ?: data.mediumImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
            error = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 2f)
                .fillMaxSize()
                .background(Color(0xFF2a292a))
        )

        TopOverlay()
        BottomOverlay(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
            ),
            title = {
                Text(data.name)
            },
            navigationIcon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onNavigateBack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            actions = favoriteAction(favoriteData, shouldShowFavoriteIcon, onFavoriteClick)
        )
    }
}

@Composable
private fun favoriteAction(
    favoritable: Favoritable,
    shouldShowFavoriteIcon: Boolean,
    onFavoriteClick: () -> Unit
): @Composable() (RowScope.() -> Unit) = {
    if (shouldShowFavoriteIcon) {
        FavoriteIcon(favoritable = favoritable) {
            onFavoriteClick()
        }
    }
}

@Composable
private fun TopOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredSize(height = 76.dp, width = Dp.Unspecified)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Black.copy(alpha = 0.75f),
                        1.0f to Color.Transparent
                    )
                )
            )
    )
}

@Composable
private fun BottomOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredSize(height = 76.dp, width = Dp.Unspecified)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Transparent,
                        1.0f to MaterialTheme.colorScheme.background,
                    )
                )
            )
    )
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_3_XL,
    showSystemUi = true
)
@Composable
fun HeaderPreview(
    @PreviewParameter(MediaPreviewParameterProvider::class) media: ShowBaseData
) {
    QuantumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Header(
                data = media,
                favoriteData = object : Favoritable { override val isFavorite get() = true },
                shouldShowFavoriteIcon = false,
                onNavigateBack = {  },
                onFavoriteClick = { },
            )
        }
    }
}
