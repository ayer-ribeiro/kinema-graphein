package dev.ayer.kinemagraphein.data.sources

import dev.ayer.kinemagraphein.data.dto.EpisodeModel
import dev.ayer.kinemagraphein.data.dto.SearchResult
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.data.dto.ShowModelComplete
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitApiService {
    @GET("search/shows")
    suspend fun search(
        @Query("q") query: String? = null
    ): List<SearchResult>?

    @GET("shows")
    suspend fun getShows(
        @Query("page") page: Int
    ): List<ShowModelBase>?

    @GET("shows/{showId}?embed[]=episodes&embed[]=seasons")
    suspend fun getShow(
        @Path("showId") showId: String
    ): ShowModelComplete?

    @GET("shows/{showId}/episodebynumber")
    suspend fun getEpisode(
        @Path("showId") showId: String,
        @Query("season") season: Int,
        @Query("number") number: Int
    ): EpisodeModel
}
