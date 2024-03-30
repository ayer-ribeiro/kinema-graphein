package dev.ayer.kinemagraphein.entity.media


data class Season(
    val summary: String?,
    val id: String,
    val isFavorite: Boolean,
    override val name: String,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
    val number: Int,
    val episodes: List<Episode>,
): Coverable
