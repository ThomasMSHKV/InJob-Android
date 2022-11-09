package com.example.injob

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.injob.databinding.ActivityMainBinding
import com.example.injob.ui.ads.AdsScreen
import com.example.injob.ui.favorites.FavoriteScreen
import com.example.injob.ui.profile.ProfileScreen
import com.example.injob.ui.responses.ResponseScreen
import com.example.injob.ui.search.SearchScreen
import com.example.injob.ui.splash.SplashScreen
import com.example.injob.utils.extensions.navigateToFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportFragmentManager.navigateToFragment(SplashScreen())

        binding?.bottomNavigationBar?.visibility = View.GONE

        Handler(Looper.getMainLooper()).postDelayed({ binding?.bottomNavigationBar?.visibility = View.VISIBLE
        }, 2500)



        binding?.bottomNavigationBar?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.searchFragment -> changeFragment(SearchScreen())
                R.id.favoriteFragment -> changeFragment(FavoriteScreen())
                R.id.profileFragment -> changeFragment(ProfileScreen())
                R.id.addPostFragment -> changeFragment(AdsScreen())
                R.id.responsesFragment -> changeFragment(ResponseScreen())
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}