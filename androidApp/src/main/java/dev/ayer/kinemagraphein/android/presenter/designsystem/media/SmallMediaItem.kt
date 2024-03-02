package dev.ayer.kinemagraphein.android.presenter.designsystem.media

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import dev.ayer.kinemagraphein.android.presenter.designsystem.favorite.FavoriteIcon

@Composable
fun SmallMediaItemCover(
    media: MediaBaseData,
    modifier: Modifier = Modifier,
    onContentClick: () -> Unit = {},
    onFavoriteIconClick: (Boolean) -> Unit = {}
) {
    Box (
        modifier = modifier.fillMaxWidth().clickable { onContentClick() }
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallMediaCover(media = media)
                MediaDescription(media = media, lines = 1)
            }
            FavoriteIcon(
                media = media,
                onClick = { onFavoriteIconClick(!media.isFavorite) }
            )
        }
    }
}


@Composable
private fun SmallMediaCover(
    media: MediaBaseData,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(media.mediumImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
            error = painterResource(id = dev.ayer.kinemagraphein.android.R.drawable.media_placeholder),
            modifier = modifier
                .size(height = 48.dp, width = Dp.Unspecified)
                .aspectRatio(2f / 3f)
                .fillMaxHeight()
                .fillMaxSize()
                .background(Color(0xFF2a292a))
        )
    }
}


@Preview(
    widthDp = 360
)
@Composable
fun SmallMediaItemCoverPreview(
    @PreviewParameter(MediaPreviewParameterProvider::class) media: MediaBaseData
) {
    SmallMediaItemCover(
        media = media
    )
}
