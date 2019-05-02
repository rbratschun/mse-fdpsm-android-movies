package at.rbratschun.mse.movies.data

class MovieUtils {
    companion object {
        fun queryTerm(term: String): String {
            if (term.isEmpty())
                return term
            try {
                val num = term.toInt()
                return num.toString()
            } catch (e: NumberFormatException) {
                return String.format("%%s%", term)
            }
        }
    }
}