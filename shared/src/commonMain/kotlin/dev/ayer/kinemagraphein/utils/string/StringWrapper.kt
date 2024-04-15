package dev.ayer.kinemagraphein.utils.string

interface Wrapper {
    val begin: String
    val end: String
}

object Parentheses : Wrapper {
    override val begin: String = "("
    override val end: String = ")"
}

object Brackets : Wrapper {
    override val begin: String = "["
    override val end: String = "]"
}

object Braces : Wrapper {
    override val begin: String = "{"
    override val end: String = "}"
}

fun CharSequence.wrapped(with: Wrapper?): CharSequence {
    return "${with?.begin ?: ""}$this${with?.end ?: ""}"
}

fun <T: CharSequence>List<T>.joinToString(
    separator: CharSequence = ", ",
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    wrapper: Wrapper? = null,
): String {
    return this.joinToString(
        separator = separator,
        prefix = prefix,
        postfix = postfix,
        limit = limit,
        truncated = truncated,
        transform = {
            it.wrapped(wrapper)
        }
    )
}
