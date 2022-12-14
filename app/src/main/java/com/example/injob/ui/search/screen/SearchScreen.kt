package com.example.injob.ui.search.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.injob.R
import com.example.injob.data.db.RoomSearchDb
import com.example.injob.databinding.SearcheScreenBinding
import com.example.injob.ui.search.adapter.SearchAdapter
import com.example.injob.ui.search.viewmodel.SearchFactory
import com.example.injob.ui.search.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class SearchScreen : Fragment() {

    private var _binding: SearcheScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel> {
        SearchFactory(
            RoomSearchDb.getAppDatabase(requireContext())?.adDao()
        )
    }
    var searchAdapter: SearchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearcheScreenBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        searchAdapter = SearchAdapter()
        searchAdapter?.launchBottomSheet = {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.search_bottom_sheet_dialog, null)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setContentView(view)
            dialog.show()
        }
        searchAdapter?.launchAdsLikeWasClicked = { adsPosition ->
            val ad = viewModel.getAllAds()!![adsPosition]
            if (ad.isLiked == false) ad.isLiked = true
            else ad.isLiked = false
            viewModel.updateAd(
                ad
            )
        }
        searchAdapter?.launchAdWasResponded = { adsPosition ->
            val ad = viewModel.getAllAds()!![adsPosition]
            if (ad.isResponded == false) ad.isResponded = true
            else ad.isResponded = false
            viewModel.updateAd(ad)
        }
        viewModel.getAllAds()?.let { searchAdapter?.setListData(it) }
        binding.recyclerViewSearch.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}