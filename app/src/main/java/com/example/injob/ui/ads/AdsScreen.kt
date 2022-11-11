package com.example.injob.ui.ads

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.injob.R
import com.example.injob.data.db.AdEntity
import com.example.injob.data.db.RoomSearchDb
import com.example.injob.databinding.AdsScreenBinding
import com.example.injob.ui.search.screen.SearchScreen
import com.example.injob.utils.constans.Constans.MIMETYPE_IMAGES
import com.example.injob.utils.extensions.navigateToFragment

class AdsScreen : Fragment() {

    private var _binding: AdsScreenBinding? = null
    private val binding get() = _binding!!

    private var currentImage: String? = null

    private val pickImage: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { contentUri ->
            binding.ivPictureAd.load(contentUri)
            currentImage = contentUri.toString()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AdsScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    private val viewModel by viewModels<AdsViewModel> {
        AdsFactory(
            RoomSearchDb.getAppDatabase(requireContext())?.adDao()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFieldsChecking()
        setClickListeners()
    }

    private fun checkFieldsBeforeActivatingButton() {
        if (binding.etTitleName.text.isNotBlank() && binding.etDescription.text.isNotBlank() &&
            binding.etLocation.text.isNotBlank() && binding.etPayment.text.isNotBlank()
            && binding.ivPictureAd.drawable != null
        ) {

            binding.btnAddAd.background = context?.let {
                ActivityCompat.getDrawable(
                    it,
                    R.drawable.btn_add_ad_ads_screen_active_background
                )
            }
            binding.btnAddAd.isClickable = true

        }
    }

    private fun setFieldsChecking() {
        binding.etTitleName.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
        binding.etDescription.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
        binding.etLocation.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
        binding.etPayment.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
    }

    private fun setClickListeners() {
        binding.btnAddAd.setOnClickListener {

            val adEntity = AdEntity(
                title = binding.etTitleName.text.toString(),
                description = binding.etDescription.text.toString(),
                payment = binding.etPayment.text.toString(),
                location = binding.etLocation.text.toString(),
                imageIsChosen = (currentImage != null),
                image = currentImage.toString()
            )
            viewModel.insertAd(adEntity)
            Toast.makeText(
                requireContext(),
                "Ваше объевление добавленно", Toast.LENGTH_LONG
            ).show()
            requireActivity().supportFragmentManager.navigateToFragment(SearchScreen())
        }
        binding.btnAddPicture.setOnClickListener {
            pickImage.launch(MIMETYPE_IMAGES)
        }
    }
}