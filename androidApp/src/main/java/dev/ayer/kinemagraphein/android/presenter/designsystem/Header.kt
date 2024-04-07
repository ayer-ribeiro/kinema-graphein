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
import androidx.compose.runtime.remember
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
import dev.ayer.kinemagraphein.android.presenter.designsystem.favorite.FavoriteIcon
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.ShowPreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.theme.QuantumTheme
import dev.ayer.kinemagraphein.entity.media.Coverable
import dev.ayer.kinemagraphein.entity.media.Favoritable
import dev.ayer.kinemagraphein.entity.media.Show

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Header(
    title: String,
    imageUrl: String?,
    isFavorite: Boolean? = null,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    val context = LocalContext.current
    val model = remember(imageUrl) {
        ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build()
    }
    val placeholderPainter = painterResource(
        id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder
    )

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        AsyncImage(
            model = model,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = placeholderPainter,
            error = placeholderPainter,
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
                Text(title)
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
            actions = favoriteAction(isFavorite = isFavorite, onFavoriteClick)
        )
    }
}

@Composable
private fun favoriteAction(
    isFavorite: Boolean?,
    onFavoriteClick: () -> Unit
): @Composable() (RowScope.() -> Unit) = {
    if (isFavorite != null) {
        FavoriteIcon(isFavorite = isFavorite) {
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
    @PreviewParameter(ShowPreviewParameterProvider::class) media: Show
) {
    QuantumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Header(
                title = "Dexter",
                imageUrl = "https://",
                isFavorite = true,
                onNavigateBack = { },
                onFavoriteClick = { },
            )
        }
    }
}