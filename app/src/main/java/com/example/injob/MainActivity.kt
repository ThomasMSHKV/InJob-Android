package com.example.injob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.injob.databinding.ActivityMainBinding
import com.example.injob.ui.ads.AdsScreen
import com.example.injob.ui.favorites.FavoriteScreen
import com.example.injob.ui.profile.ProfileScreen
import com.example.injob.ui.responses.ResponseScreen
import com.example.injob.ui.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        changeFragment(SearchScreen())

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