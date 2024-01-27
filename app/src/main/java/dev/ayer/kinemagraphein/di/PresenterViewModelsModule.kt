package dev.ayer.kinemagraphein.di

import dev.ayer.kinemagraphein.presenter.episode.EpisodeViewModel
import dev.ayer.kinemagraphein.presenter.home.HomeViewModel
import dev.ayer.kinemagraphein.presenter.series.ShowScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { HomeViewModel() }
    viewModel { ShowScreenViewModel(get()) }
    viewModel { EpisodeViewModel(get(), get(), get()) }
}
