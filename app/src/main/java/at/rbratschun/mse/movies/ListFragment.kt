package at.rbratschun.mse.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.rbratschun.mse.movies.data.MovieAdapter
import at.rbratschun.mse.movies.data.MovieDb
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private val LOG_TAG = "ListFragment"
    lateinit var adapter: MovieAdapter
    lateinit var db: MovieDb

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(LOG_TAG, "ListFragment started ...")
        println("ListFragment started ...");
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            println("clicked ....")
            Navigation.findNavController(view).navigate(R.id.detailFragment)
        }
        bootstrap()
    }

    fun bootstrap() {
        db = MovieDb()
        adapter = MovieAdapter(db.fetchMovieCollections(), requireContext())
        setupRecyclerView()
        setupSearchListener()
    }

    fun setupSearchListener() {
        search_action.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                println(p0.toString())
                filter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    fun filter(text: String) {
        if (text.length < 3) {
            adapter.filter(db.movieCollection)
        } else {
            adapter.filter(db.search(text));
        }
    }
}