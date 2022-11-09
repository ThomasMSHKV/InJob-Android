package com.example.injob.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.example.injob.R
import com.example.injob.ui.search.SearchScreen
import com.example.injob.utils.extensions.navigateToFragment

class SplashScreen : Fragment(R.layout.splash_screen) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            requireActivity().supportFragmentManager.navigateToFragment(SearchScreen())
        }, 2500)

    }


}