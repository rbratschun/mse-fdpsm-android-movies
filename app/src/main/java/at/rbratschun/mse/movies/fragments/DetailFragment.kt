package at.rbratschun.mse.movies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import at.rbratschun.mse.movies.R
import at.rbratschun.mse.movies.data.Movie
import at.rbratschun.mse.movies.data.MovieDb
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private var movie: Movie? = null

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
        prepareViews()
        fetchDetails()
    }

    private fun prepareViews() {
        activity?.title = args.title
        detailMovieTitle.text = args.title
        detailMovieYear.text = resources.getString(R.string.movie_published, args.year)
        Picasso.get().load(args.poster).into(detailMoviePoster)
    }

    private fun fetchDetails() {
        doAsync {
            val result = MovieDb.instance.getDetails(args.imdbId)
            uiThread {
                movie = result
                detailMovieWriter?.text = resources.getString(R.string.movie_writer_content, movie?.Writer)
                detailMoviePlot?.text = resources.getString(R.string.movie_plot_content, movie?.Plot)
            }
        }
    }
}