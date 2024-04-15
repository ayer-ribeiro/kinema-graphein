package dev.ayer.kinemagraphein.android.presenter.imageresource

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import dev.ayer.kinemagraphein.android.R

typealias FavoriteIllustration = ImageResource.Illustration.Favorite

@Immutable
sealed class ImageResource(
    @DrawableRes val drawableResId: Int,
    @StringRes val contentDescription: Int,
) {
    @Immutable
    sealed class Illustration(
        @DrawableRes drawableResId: Int,
        @StringRes contentDescription: Int,
    ) : ImageResource(drawableResId = drawableResId, contentDescription = contentDescription) {

        @Immutable
        data object Favorite : Illustration(
            drawableResId = R.drawable.ilustration_favorite,
            contentDescription = R.string.home_favorite_illustration_content_description
        )
    }
}

fun Context.getContentDescription(resource: ImageResource): String {
    return this.getString(resource.contentDescription)
}