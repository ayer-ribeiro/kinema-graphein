package dev.ayer.kinemagraphein.android.presenter.designsystem.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ayer.kinemagraphein.entity.media.MediaBaseData

@Composable
fun FavoriteIcon(
    media: MediaBaseData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box (
        modifier = modifier.size(48.dp).clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        if (media.isFavorite) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        } else {
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }
}
