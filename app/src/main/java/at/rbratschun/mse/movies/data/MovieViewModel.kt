package at.rbratschun.mse.movies.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepository(application)
    val movies = MediatorLiveData<List<MovieEntity>>()
    val selectedMovie = MediatorLiveData<MovieEntity>()

    fun findAll() {
        movies.addSource(repository.findAll()) { movies.value = it }
    }

    fun search(term: String) {
        movies.addSource(repository.search(term)) { movies.value = it }
    }

    fun moviesToWatch() {
        movies.addSource(repository.moviesToWatch()) { movies.value = it }
    }

    fun favoriteMovies() {
        movies.addSource(repository.favoriteMovies()) { movies.value = it }
    }

    fun findById(id: String) {
        selectedMovie.addSource(repository.findById(id)) { selectedMovie.value = it }
    }

    fun update(movie: MovieEntity) {
        repository.update(movie)
    }
}