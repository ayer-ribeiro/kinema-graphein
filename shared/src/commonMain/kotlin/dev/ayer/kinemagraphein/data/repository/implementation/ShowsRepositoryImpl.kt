package dev.ayer.kinemagraphein.data.repository.implementation

import dev.ayer.kinemagraphein.core.repository.ShowsRepository
import dev.ayer.kinemagraphein.data.api.adapter.toShow
import dev.ayer.kinemagraphein.data.api.KtorfitApiService
import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShowsRepositoryImpl: ShowsRepository, KoinComponent {

    private val ktorfitApiService: KtorfitApiService by inject()

    private val showStateFlow = MutableStateFlow<Show?>(null)

    override suspend fun fetchShow(id: String): Flow<Show?> {
//        val result = ktorfitApiService.getShow(id)?.toShow()
//        showStateFlow.emit(result)
        return showStateFlow
    }
}
