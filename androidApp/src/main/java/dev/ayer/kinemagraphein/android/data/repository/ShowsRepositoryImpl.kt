package dev.ayer.kinemagraphein.android.data.repository

import dev.ayer.kinemagraphein.core.repository.ShowsRepository
import dev.ayer.kinemagraphein.data.adapter.toShow
import dev.ayer.kinemagraphein.data.sources.RetrofitApiService
import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShowsRepositoryImpl: ShowsRepository, KoinComponent {

    private val retrofitApiService: RetrofitApiService by inject()

    private val showStateFlow = MutableStateFlow<Show?>(null)

    override suspend fun fetchShow(id: String): Flow<Show?> {
        val result = retrofitApiService.getShow(id)?.toShow()
        showStateFlow.emit(result)
        return showStateFlow
    }
}
