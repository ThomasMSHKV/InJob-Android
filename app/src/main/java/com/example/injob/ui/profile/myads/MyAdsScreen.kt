package com.example.injob.ui.profile.myads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.injob.R
import com.example.injob.data.db.RoomSearchDb
import com.example.injob.databinding.MyAdsScreenBinding
import com.example.injob.ui.profile.viewmodel.ProfileFactory
import com.example.injob.ui.profile.viewmodel.ProfileViewModel
import com.example.injob.ui.search.adapter.SearchAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class MyAdsScreen : Fragment() {

    private var _binding: MyAdsScreenBinding? = null
    private val binding get() = _binding!!
    var adapter: SearchAdapter? = null
    private val viewModel by viewModels<ProfileViewModel> {
        ProfileFactory(
            RoomSearchDb.getAppDatabase(requireContext())?.adDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyAdsScreenBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        adapter?.launchAdWasResponded = { adsPosition ->
            val ad = viewModel.getAllAds()!![adsPosition]
            if (ad.isResponded == false) ad.isResponded = true
            else ad.isResponded = false
            viewModel.updateAd(ad)
        }
        binding.recyclerViewMyAds.adapter = adapter
        binding.recyclerViewMyAds.layoutManager = LinearLayoutManager(requireContext())
        adapter?.listOfAds = viewModel.getAllAds()!!
    }
}