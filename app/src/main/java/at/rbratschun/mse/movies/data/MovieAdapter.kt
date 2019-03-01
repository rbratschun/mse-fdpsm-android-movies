package at.rbratschun.mse.movies.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import at.rbratschun.mse.movies.ListFragmentDirections
import at.rbratschun.mse.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(var data: ArrayList<MovieCollection>, val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onBindViewHolder(holder: MovieAdapter.MovieHolder, position: Int) {
        holder.bindMovie(data.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieHolder {
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(inflatedView)
    }

    override fun getItemCount() = data.size

    fun filter(list: ArrayList<MovieCollection>) {
        this.data = list
        notifyDataSetChanged()
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var movie: MovieCollection? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                this.movie!!.Title,
                this.movie!!.Year.toString(),
                this.movie!!.Poster
            )
            Navigation.findNavController(v).navigate(action)
        }

        fun bindMovie(movie: MovieCollection) {
            this.movie = movie
            Picasso.get().load(movie.Poster).into(view.movie_collection_poster)
            view.movie_collection_title.text = movie.Title
            view.movie_collection_year.text = "Veröffentlicht: " + movie.Year
        }
    }
}