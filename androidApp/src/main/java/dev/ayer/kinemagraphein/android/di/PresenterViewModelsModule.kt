package dev.ayer.kinemagraphein.android.di

import dev.ayer.kinemagraphein.android.presenter.screens.episode.EpisodeViewModel
import dev.ayer.kinemagraphein.android.presenter.screens.home.HomeViewModel
import dev.ayer.kinemagraphein.android.presenter.screens.series.ShowScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel { HomeViewModel() }
    viewModel { ShowScreenViewModel(get()) }
    viewModel { EpisodeViewModel(get(), get(), get()) }
}
