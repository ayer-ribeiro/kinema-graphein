package dev.ayer.kinemagraphein.android.di

import dev.ayer.kinemagraphein.android.core.repository.EpisodeRepository
import dev.ayer.kinemagraphein.android.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.android.core.repository.ShowsRepository
import dev.ayer.kinemagraphein.android.data.repository.EpisodeRepositoryImpl
import dev.ayer.kinemagraphein.android.data.repository.MediaBaseRepositoryImpl
import dev.ayer.kinemagraphein.android.data.repository.ShowsRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    factory<EpisodeRepository> { EpisodeRepositoryImpl() }
    factory<MediaBaseRepository> { MediaBaseRepositoryImpl() }
    factory<ShowsRepository> { ShowsRepositoryImpl() }
}
