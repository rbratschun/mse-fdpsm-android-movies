package at.rbratschun.mse.movies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import at.rbratschun.mse.movies.R
import at.rbratschun.mse.movies.data.Movie
import at.rbratschun.mse.movies.data.MovieDb
import at.rbratschun.mse.movies.data.MovieEntity
import at.rbratschun.mse.movies.data.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private var movie: Movie? = null
    private lateinit var movieEntity: MovieEntity
    private lateinit var movies: MovieViewModel

    companion object {
        private val LOG_TAG = DetailFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailMoviePoster.setOnClickListener {
            val action =
                DetailFragmentDirections.actionDetailFragmentToPosterFragment(
                    args.poster
                )
            Navigation.findNavController(it).navigate(action)
        }
        movies = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        prepareViews()
        fetchDetails()
    }

    private fun prepareViews() {
        activity?.title = args.title
        detailMovieTitle.text = args.title
        detailMovieYear.text = resources.getString(R.string.movie_published, args.year)
        Picasso.get().load(args.poster).into(detailMoviePoster)
    }

    private val movieObserver =
        Observer<MovieEntity> { value ->
            value?.let { movieEntity = value }
        }

    private fun fetchDetails() {
        movies.selectedMovie.observe(requireActivity(), movieObserver)
        movies.findById(args.imdbId)
        doAsync {
            val result = MovieDb.instance.getDetails(args.imdbId)
            uiThread {
                movie = result
                detailMovieWriter?.text = resources.getString(R.string.movie_writer_content, movie?.Writer)
                detailMoviePlot?.text = resources.getString(R.string.movie_plot_content, movie?.Plot)
            }
        }
    }

    private fun toggleToWatch() {
        movieEntity.toWatch = !movieEntity.toWatch
        doAsync {
            movies.update(movieEntity)
        }
    }

    private fun toggleFavorite() {
        movieEntity.favorite = !movieEntity.favorite
        doAsync {
            movies.update(movieEntity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.miFavorites) {
            toggleFavorite()
        } else if (id == R.id.miToWatch) {
            toggleToWatch()
        } else if (id == R.id.miAllMovies) {
            toggleFavorite()
            toggleToWatch()
        }
        return super.onOptionsItemSelected(item)
    }
}