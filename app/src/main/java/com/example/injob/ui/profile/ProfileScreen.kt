package com.example.injob.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.injob.R
import com.example.injob.databinding.FaqScreenBinding
import com.example.injob.databinding.ProfileScreenBinding
import com.example.injob.ui.profile.faq.FAQScreen
import com.example.injob.utils.extensions.navigateToFragment


class ProfileScreen : Fragment() {

    private var _binding: ProfileScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = ProfileScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileFAQ.setOnClickListener {
            fragmentManager?.navigateToFragment(FAQScreen())
        }
    }

}