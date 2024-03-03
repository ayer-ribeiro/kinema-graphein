package dev.ayer.kinemagraphein.android.di

import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.core.usecase.FetchEpisodeDataUseCase
import dev.ayer.kinemagraphein.core.usecase.FetchSeriesDataUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadFavoritesUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadRecentUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadMoreShowItemsUseCase
import dev.ayer.kinemagraphein.core.usecase.SearchMediaUseCase
import dev.ayer.kinemagraphein.android.domain.ChangeFavoriteStateUseCaseImpl
import dev.ayer.kinemagraphein.android.domain.FetchEpisodeDataUseCaseImpl
import dev.ayer.kinemagraphein.android.domain.FetchSeriesDataUseCaseImpl
import dev.ayer.kinemagraphein.android.domain.LoadFavoritesUseCaseImpl
import dev.ayer.kinemagraphein.android.domain.LoadRecentUseCaseImpl
import dev.ayer.kinemagraphein.android.domain.LoadMoreShowItemsUseCaseImpl
import dev.ayer.kinemagraphein.android.domain.SearchMediaUseCaseImpl
import org.koin.dsl.module

val useCaseModules = module {
    factory<ChangeFavoriteStateUseCase> { ChangeFavoriteStateUseCaseImpl() }
    factory<LoadRecentUseCase> { LoadRecentUseCaseImpl() }
    factory<LoadFavoritesUseCase> { LoadFavoritesUseCaseImpl() }
    factory<FetchSeriesDataUseCase> { FetchSeriesDataUseCaseImpl() }
    factory<SearchMediaUseCase> { SearchMediaUseCaseImpl() }
    factory<LoadMoreShowItemsUseCase> { LoadMoreShowItemsUseCaseImpl() }
    factory<FetchEpisodeDataUseCase> { FetchEpisodeDataUseCaseImpl() }
}
