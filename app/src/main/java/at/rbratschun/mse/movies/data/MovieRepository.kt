package at.rbratschun.mse.movies.data

import android.app.Application
import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class MovieRepository(application: Application) {
    private val dao: MovieDao
    private val list: LiveData<List<MovieEntity>>

    init {
        val db = MovieDatabase.getInstance(application)
        dao = db?.movieDao()!!
        populate()
        list = dao.findAll()
    }

    fun findAll(): LiveData<List<MovieEntity>> {
        return dao.findAll()
    }

    fun findById(id: String): LiveData<MovieEntity> {
        return dao.findById(id)
    }

    fun search(term: String): LiveData<List<MovieEntity>> {
        return dao.search(MovieUtils.queryTerm(term))
    }

    fun moviesToWatch(): LiveData<List<MovieEntity>> {
        return dao.moviesToWatch()
    }

    fun favoriteMovies(): LiveData<List<MovieEntity>> {
        return dao.favoriteMovies()
    }

    private fun create(movie: MovieCollection): Long {
        return dao.create(
            MovieEntity(movie.imdbID, movie.Title, movie.Year, movie.Type, movie.Poster, false, false)
        )
    }

    private fun create(movies: List<MovieCollection>) {
        for (movie in movies) {
            create((movie))
        }
    }

    fun update(movie: MovieEntity) {
        dao.update(movie)
    }

    private fun populate() {
        doAsync {
            val movieDb = MovieDb.instance
            movieDb.downloadMovies()
            create(movieDb.search(""))
        }
    }
}