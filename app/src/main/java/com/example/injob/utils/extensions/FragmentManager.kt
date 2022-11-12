package com.example.injob.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.injob.R

fun FragmentManager.navigateToFragment(fragment: Fragment, addToBackStack: Boolean = true) {
    if (addToBackStack) {
        beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    } else {
        beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}