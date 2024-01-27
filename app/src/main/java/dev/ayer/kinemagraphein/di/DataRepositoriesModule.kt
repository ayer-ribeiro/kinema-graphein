package dev.ayer.kinemagraphein.di

import dev.ayer.kinemagraphein.core.repository.EpisodeRepository
import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.repository.ShowsRepository
import dev.ayer.kinemagraphein.data.repository.EpisodeRepositoryImpl
import dev.ayer.kinemagraphein.data.repository.MediaBaseRepositoryImpl
import dev.ayer.kinemagraphein.data.repository.ShowsRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    factory<EpisodeRepository> { EpisodeRepositoryImpl() }
    factory<MediaBaseRepository> { MediaBaseRepositoryImpl() }
    factory<ShowsRepository> { ShowsRepositoryImpl() }
}
