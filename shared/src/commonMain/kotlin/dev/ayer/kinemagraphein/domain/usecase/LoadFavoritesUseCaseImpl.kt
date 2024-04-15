package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.repository.QueryLimit
import dev.ayer.kinemagraphein.core.usecase.LoadFavoritesUseCase
import dev.ayer.kinemagraphein.entity.UserFavorite
import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class LoadFavoritesUseCaseImpl : LoadFavoritesUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()
    private val limit = QueryLimit(10L)

    override suspend fun invoke(): Flow<UserFavorite> {
        return mediaBaseRepository.loadFavoriteMedia(limit = limit)
    }
}
