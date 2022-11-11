package com.example.injob.ui.profile.faq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.injob.R
import com.example.injob.databinding.AdsScreenBinding
import com.example.injob.databinding.FaqScreenBinding


class FAQScreen : Fragment() {
    private var _binding: FaqScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FaqScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

}