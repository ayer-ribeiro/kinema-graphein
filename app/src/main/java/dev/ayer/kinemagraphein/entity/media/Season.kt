package dev.ayer.kinemagraphein.entity.media


data class Season(
    override val summary: String?,
    override val id: String,
    override val isFavorite: Boolean,
    override val name: String,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
    val number: Int,
    val episodes: List<Episode>,
): MediaBaseData
