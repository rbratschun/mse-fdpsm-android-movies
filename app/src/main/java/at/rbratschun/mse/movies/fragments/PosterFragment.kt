package at.rbratschun.mse.movies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import at.rbratschun.mse.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_poster.*

class PosterFragment : Fragment() {
    private val args: PosterFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_poster, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Picasso.get().load(args.poster).into(fullMoviePoster)
    }
}