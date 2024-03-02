package dev.ayer.kinemagraphein.android.presenter.designsystem.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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
import coil.request.CachePolicy
import coil.request.ImageRequest
import dev.ayer.kinemagraphein.android.entity.media.MediaBaseData
import dev.ayer.kinemagraphein.android.presenter.designsystem.favorite.FavoriteIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaItemCover(
    media: MediaBaseData,
    modifier: Modifier = Modifier,
    onContentClick: () -> Unit = {},
    onFavoriteIconClick: (Boolean) -> Unit = {}
) {
    Box (
        contentAlignment = Alignment.TopEnd,
        modifier = modifier
    ) {
        Card(
            modifier = modifier,
            onClick = onContentClick
        ) {
            MediaCover(media = media)
            MediaDescription(media = media, lines = 2)
        }
        FavoriteIcon(
            media = media,
        ) {
            onFavoriteIconClick(!media.isFavorite)
        }
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
    media: MediaBaseData,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(media.mediumImageUrl)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
            error = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
            modifier = modifier

                .aspectRatio(2f / 3f)
                .fillMaxHeight()
                .fillMaxSize()
                .background(Color(0xFF2a292a))
        )
        TopOverlay(modifier = Modifier.align(Alignment.TopStart))
    }
}

@Preview(
    widthDp = 120
)
@Composable
fun MediaItemCoverPreview(
    @PreviewParameter(MediaPreviewParameterProvider::class) media: MediaBaseData
) {
    MediaItemCover(
        media = media
    )
}
