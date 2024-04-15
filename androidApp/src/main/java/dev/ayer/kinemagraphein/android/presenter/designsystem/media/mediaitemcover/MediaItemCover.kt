package dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import dev.ayer.kinemagraphein.android.presenter.designsystem.favorite.FavoriteIcon
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.MediaDescription
import dev.ayer.kinemagraphein.android.presenter.theme.BrandBlue
import dev.ayer.kinemagraphein.android.presenter.theme.BrandPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaItemCover(
    media: MediaItemCoverUiState,
    modifier: Modifier = Modifier,
    onContentClick: () -> Unit = {},
    onFavoriteIconClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = modifier
    ) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            onClick = onContentClick
        ) {
            MediaCover(imageUrl = media.imageUrl)
            MediaDescription(description = media.title, lines = 2)
        }
        FavoriteIcon(
            isFavorite = media.isFavorite,
            onClick = onFavoriteIconClick
        )
    }
}

@Composable
private fun TopOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredSize(height = 48.dp, width = Dp.Unspecified)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Black.copy(alpha = 0.75f),
                        1.0f to Color.Transparent,
                    )
                )
            )
    )
}

@Composable
private fun MediaCover(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val model = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
    )
    
    Box(modifier = modifier.background(Color(0xFF2a292a))) {
        Image(
            painter = model,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .aspectRatio(2f / 3f)
                .fillMaxHeight()
                .fillMaxSize()

        )
        TopOverlay(modifier = Modifier.align(Alignment.TopStart))
    }
}

@Preview(
    widthDp = 120
)
@Composable
fun MediaItemCoverPreview(
    @PreviewParameter(MediaItemCoverUiStatePreviewParameterProvider::class) media: MediaItemCoverUiState
) {
    MediaItemCover(
        media = media
    )
}