package com.example.injob.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.injob.R
import com.example.injob.data.db.RoomSearchDb
import com.example.injob.databinding.FavoriteScreenBinding
import com.example.injob.ui.search.adapter.SearchAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class FavoriteScreen : Fragment() {

    private var _binding: FavoriteScreenBinding? = null
    private val binding get() = _binding!!
    private var adapter: SearchAdapter? = null
    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteFactory(
            RoomSearchDb.getAppDatabase(requireContext())?.adDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteScreenBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = SearchAdapter()
        adapter?.launchBottomSheet = {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.search_bottom_sheet_dialog, null)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setContentView(view)
            dialog.show()
        }
        adapter?.launchAdsLikeWasClicked = { adsPosition ->
            val ad = viewModel.getAllAds()!![adsPosition]
            if (ad.isLiked == false) ad.isLiked = true
            else ad.isLiked = false
            viewModel.updateAd(ad)
        }
        binding.recyclerViewFavorite.adapter = adapter
        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(requireContext())
        adapter?.listOfAds = viewModel.getAllLikedAds()!!
    }
}