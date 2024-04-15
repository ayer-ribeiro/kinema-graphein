package dev.ayer.kinemagraphein.core.repository

class QueryLimit(val value: Long) {

    fun hasLimit() = this.value != NoLimit.value

    companion object {
        val NoLimit = QueryLimit(-1)
        fun Long.asQueryLimit() = QueryLimit(this)
    }
}