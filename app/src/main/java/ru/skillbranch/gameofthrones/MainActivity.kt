package ru.skillbranch.gameofthrones

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.skillbranch.gameofthrones.ui.main.splash.SplashFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        savedInstanceState ?: prepareData()
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
    }

    private fun prepareData() {
        viewModel.syncDataIfNeed().observe(this, Observer<LoadResult<Boolean>> {
            when(it) {
                is LoadResult.Loading -> {
                    navController.navigate(R.id.startScreen)
                }
                is LoadResult.Success -> {
//                    val action = SplashFragmentDirections.actionNavSplashToNavHouses()
//                    navController.navigate(action)
                }
            }
        })
    }
}