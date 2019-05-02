package at.rbratschun.mse.movies.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.rbratschun.mse.movies.R
import at.rbratschun.mse.movies.data.MovieAdapter
import at.rbratschun.mse.movies.data.MovieEntity
import at.rbratschun.mse.movies.data.MovieViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    private lateinit var movies: MovieViewModel

    companion object {
        private val LOG_TAG = ListFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = "Movies"
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bootstrap()
    }

    private fun bootstrap() {
        movies = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        adapter = MovieAdapter(ArrayList(), requireContext())
        movies.movies.observe(this, movieObserver)
        movies.findAll()
        setupRecyclerView()
        setupSearchListener()
    }


    private val movieObserver =
        Observer<List<MovieEntity>> { value ->
            value?.let { adapter.filter(value) }
        }

    private fun setupSearchListener() {
        search_action.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    private fun filter(term: String) {
        try {
            when (term.isEmpty()) {
                (true) -> movies.findAll()
                (false) -> movies.search(String.format("%%s%", term))
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "error filter by term ", e)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miFavorites -> movies.favoriteMovies()
            R.id.miToWatch -> movies.moviesToWatch()
            R.id.miAllMovies -> movies.findAll()
        }
        return super.onOptionsItemSelected(item)
    }
}