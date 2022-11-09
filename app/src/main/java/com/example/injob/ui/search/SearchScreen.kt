package com.example.injob.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.injob.databinding.SearcheScreenBinding
import com.example.injob.ui.search.adapter.SearchAdapter

class SearchScreen : Fragment() {

    private var _binding: SearcheScreenBinding? = null
    private val binding get() = _binding!!
    var viewModel: SearchViewModel? = null
    var searchAdapter: SearchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearcheScreenBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

    }

    private fun initAdapter() {
        searchAdapter = SearchAdapter()
        binding.recyclerViewSearch.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
