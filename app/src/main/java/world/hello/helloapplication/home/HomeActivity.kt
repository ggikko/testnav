package world.hello.helloapplication.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_home.*
import world.hello.helloapplication.R
import world.hello.helloapplication.disableShiftMode
import world.hello.helloapplication.setUpWithNav

class HomeActivity : AppCompatActivity() {

    lateinit var homeViewmodel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewmodel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        setUpWithNav(
            bottomNavigation,
            findNavController(R.id.navHostFragment)
        )
        disableShiftMode(bottomNavigation)
    }
}
