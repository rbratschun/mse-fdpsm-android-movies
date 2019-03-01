package at.rbratschun.mse.movies

import android.os.Bundle
import android.util.Log
import android.view.Menu
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
        Log.d("MainActivity", "MainActivity: onCreate")
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
}
