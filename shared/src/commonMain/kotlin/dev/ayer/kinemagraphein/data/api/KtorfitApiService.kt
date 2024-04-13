package dev.ayer.kinemagraphein.data.api

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import dev.ayer.kinemagraphein.data.dto.EpisodeModel
import dev.ayer.kinemagraphein.data.dto.SearchResult
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.data.dto.ShowModelComplete


interface KtorfitApiService {
    @GET("search/shows")
    suspend fun search(
        @Query("q") query: String? = null
    ): List<SearchResult>?

    @GET("shows")
    suspend fun getShows(
        @Query("page") page: Long
    ): List<ShowModelBase>?

    @GET("shows/{showId}?embed[]=episodes&embed[]=seasons")
    suspend fun getShow(
        @Path("showId") showId: Long
    ): ShowModelComplete?

    @GET("shows/{showId}/episodebynumber")
    suspend fun getEpisode(
        @Path("showId") showId: String,
        @Query("season") season: Int,
        @Query("number") number: Int
    ): EpisodeModel?
}
