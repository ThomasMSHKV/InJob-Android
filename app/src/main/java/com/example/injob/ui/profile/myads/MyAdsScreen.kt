package com.example.injob.ui.profile.myads

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.injob.R
import com.example.injob.data.db.RoomSearchDb
import com.example.injob.databinding.MyAdsScreenBinding
import com.example.injob.ui.profile.viewmodel.ProfileFactory
import com.example.injob.ui.profile.viewmodel.ProfileViewModel
import com.example.injob.ui.search.adapter.SearchAdapter
import com.example.injob.ui.search.viewmodel.SearchFactory
import com.google.android.material.bottomsheet.BottomSheetDialog


class MyAdsScreen : Fragment() {

    private var _binding: MyAdsScreenBinding? = null
    private val binding get() = _binding!!
    var searchAdapter: SearchAdapter? = null
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
        searchAdapter = SearchAdapter {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.search_bottom_sheet_dialog, null)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setContentView(view)
            dialog.show()
        }
        viewModel.getAllAds()?.let { searchAdapter?.setListData(it) }
        binding.recyclerViewMyAds.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}