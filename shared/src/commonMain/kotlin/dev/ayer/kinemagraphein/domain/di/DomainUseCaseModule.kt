package dev.ayer.kinemagraphein.domain.di

import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.core.usecase.FetchEpisodeDataUseCase
import dev.ayer.kinemagraphein.core.usecase.FetchShowDataUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadFavoritesUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadRecentUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadMoreShowItemsUseCase
import dev.ayer.kinemagraphein.core.usecase.SearchMediaUseCase
import dev.ayer.kinemagraphein.domain.usecase.ChangeFavoriteStateUseCaseImpl
import dev.ayer.kinemagraphein.domain.usecase.FetchEpisodeDataUseCaseImpl
import dev.ayer.kinemagraphein.domain.usecase.FetchShowDataUseCaseImpl
import dev.ayer.kinemagraphein.domain.usecase.LoadFavoritesUseCaseImpl
import dev.ayer.kinemagraphein.domain.usecase.LoadRecentUseCaseImpl
import dev.ayer.kinemagraphein.domain.usecase.LoadMoreShowItemsUseCaseImpl
import dev.ayer.kinemagraphein.domain.usecase.SearchMediaUseCaseImpl
import org.koin.dsl.module

internal val useCaseModules = module {
    factory<ChangeFavoriteStateUseCase> { ChangeFavoriteStateUseCaseImpl() }
    factory<LoadRecentUseCase> { LoadRecentUseCaseImpl() }
    factory<LoadFavoritesUseCase> { LoadFavoritesUseCaseImpl() }
    factory<FetchShowDataUseCase> { FetchShowDataUseCaseImpl() }
    factory<SearchMediaUseCase> { SearchMediaUseCaseImpl() }
    factory<LoadMoreShowItemsUseCase> { LoadMoreShowItemsUseCaseImpl() }
    factory<FetchEpisodeDataUseCase> { FetchEpisodeDataUseCaseImpl() }
}
