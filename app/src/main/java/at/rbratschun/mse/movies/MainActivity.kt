package at.rbratschun.mse.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupNavigationDrawer(toolbar)
    }

    private fun setupNavigationDrawer(toolbar: Toolbar) {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val configuration =
            AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, configuration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.navigation_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.miFavorites) {
            Toast.makeText(this, getText(R.string.activity_favorites_not_implemented), Toast.LENGTH_SHORT).show()
        } else if (id == R.id.miToWatch) {
            Toast.makeText(this, getText(R.string.activity_watchlist_not_implemented), Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
