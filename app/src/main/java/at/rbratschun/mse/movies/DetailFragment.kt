package at.rbratschun.mse.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailMovieTitle.text = args.title
        detailMovieYear.text = "Veröffentlicht in: " + args.year
        Picasso.get().load(args.poster).into(detailMoviePoster)
        detailMoviePoster.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToPosterFragment(this.args!!.poster)
            Navigation.findNavController(it).navigate(action)
        }
    }

}