package at.rbratschun.mse.movies.data

import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class MovieDb {
    private var initialized = false

    companion object {
        val instance = MovieDb()
        var movieCollection: ArrayList<MovieCollection> = ArrayList()
        private const val LOG_TAG = "MovieDb"
    }

    private fun mockMovieCollections(): ArrayList<MovieCollection> {
        movieCollection.add(
            MovieCollection(
                "Bee Movie",
                2007,
                "movie",
                "tt0389790",
                "https://m.media-amazon.com/images/M/MV5BMjE1MDYxOTA4MF5BMl5BanBnXkFtZTcwMDE0MDUzMw@@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Epic Movie",
                2007,
                "movie",
                "tt0389790",
                "https://m.media-amazon.com/images/M/MV5BMTA3NDM5ODU3NzleQTJeQWpwZ15BbWU3MDgyMjgyNDE@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Movie 43",
                2013,
                "movie",
                "tt0389790",
                "https://m.media-amazon.com/images/M/MV5BMTg4NzQ3NDM1Nl5BMl5BanBnXkFtZTcwNjEzMjM3OA@@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie",
                2000,
                "movie",
                "tt0175142",
                "https://m.media-amazon.com/images/M/MV5BMGEzZjdjMGQtZmYzZC00N2I4LThiY2QtNWY5ZmQ3M2ExZmM4XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie 2",
                2001,
                "movie",
                "tt0257106",
                "https://m.media-amazon.com/images/M/MV5BMzQxYjU1OTUtYjRiOC00NDg2LWI4MWUtZGU5YzdkYTcwNTBlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie 3",
                2003,
                "movie",
                "tt0306047",
                "https://m.media-amazon.com/images/M/MV5BNDE2NTIyMjg2OF5BMl5BanBnXkFtZTYwNDEyMTg3._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie 4",
                2006,
                "movie",
                "tt0362120",
                "https://m.media-amazon.com/images/M/MV5BZmFkMzc2NTctN2U1Ni00MzE5LWJmMzMtYWQ4NjQyY2MzYmM1XkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Lego Batman Movie",
                2017,
                "movie",
                "tt4116284",
                "https://m.media-amazon.com/images/M/MV5BMTcyNTEyOTY0M15BMl5BanBnXkFtZTgwOTAyNzU3MDI@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Lego Movie",
                2014,
                "movie",
                "tt1490017",
                "https://m.media-amazon.com/images/M/MV5BMTg4MDk1ODExN15BMl5BanBnXkFtZTgwNzIyNjg3MDE@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Shawshank Redemption",
                1996,
                "movie",
                "tt0111161",
                "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Simpsons Movie",
                2007,
                "movie",
                "tt0462538",
                "https://m.media-amazon.com/images/M/MV5BMTgxMDczMTA5N15BMl5BanBnXkFtZTcwMzk1MzMzMw@@._V1_SX300.jpg"
            )
        )
        return movieCollection
    }

    fun downloadMovies(): Boolean {
        val url = "https://www.omdbapi.com/?s=movie&y=2018&apikey=7b79da76"
        Log.d(LOG_TAG, "fetching movies ...")
        if (!initialized) {
            try {
                val movies = jacksonObjectMapper()
                    .readValue<MovieSearchResult>(URL(url).readText()).Search
                movieCollection = movies
                Log.d(LOG_TAG, "downloaded $movieCollection.size movies ...")
            } catch (e: Exception) {
                Log.e(LOG_TAG, "error downloading movies: ", e)
                movieCollection = mockMovieCollections()
            } finally {
                initialized = !initialized
                return true
            }
        }
        return false
    }

    fun search(q: String): ArrayList<MovieCollection> {
        return when (q.isEmpty()) {
            true -> movieCollection
            false -> ArrayList(movieCollection.filter { m -> m.Title.contains(q) })
        }
    }

    fun getDetails(imdbId: String): Movie? {
        val url = "https://www.omdbapi.com/?i=$imdbId&apiKey=7b79da76"
        Log.d(LOG_TAG, "fetching movie details with url: $url")
        var movie: Movie? = null
        try {
            movie = jacksonObjectMapper()
                .readValue<Movie>(
                    URL(url).readText()
                )
        } catch (e: Exception) {
            Log.e(LOG_TAG, "error fetching movie detail for imdbId $imdbId: $e.message")
        }
        return movie
    }
}