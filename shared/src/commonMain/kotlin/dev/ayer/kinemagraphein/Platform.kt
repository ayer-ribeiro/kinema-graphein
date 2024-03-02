package dev.ayer.kinemagraphein

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform