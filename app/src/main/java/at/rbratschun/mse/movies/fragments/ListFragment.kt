package at.rbratschun.mse.movies.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.rbratschun.mse.movies.R
import at.rbratschun.mse.movies.data.MovieAdapter
import at.rbratschun.mse.movies.data.MovieDb
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class ListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    private lateinit var db: MovieDb

    companion object {
        private const val LOG_TAG = "ListFragment"
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
        db = MovieDb.instance
        adapter = MovieAdapter(ArrayList(), requireContext())
        doAsync {
            val weHaveUpdates = db.downloadMovies()
            uiThread {
                if (weHaveUpdates) {
                    Toast.makeText(context, "Movies successully downloaded", Toast.LENGTH_SHORT).show()
                }
                adapter.filter(db.search(""))
            }
        }
        setupRecyclerView()
        setupSearchListener()
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
        doAsync {
            try {
                val movies = db.search(term)
                uiThread {
                    adapter.filter(movies)
                }
            } catch (e: Exception) {
                Log.e(LOG_TAG, "error filter by term ", e)
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}