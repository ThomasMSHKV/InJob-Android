package com.example.injob.ui.ads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.injob.R
import com.example.injob.data.db.AdEntity
import com.example.injob.databinding.AdsScreenBinding
import com.example.injob.databinding.SearcheScreenBinding
import com.example.injob.utils.constans.Constans.Companion.MIMETYPE_IMAGES
import kotlinx.android.synthetic.main.ads_screen.*

class AdsScreen : Fragment() {

    private var _binding: AdsScreenBinding? = null
    private val binding get() = _binding!!

    private val picImage: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { contentUri ->
            binding.ivPicturePlaceholder.load(contentUri)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AdsScreenBinding.inflate(layoutInflater, container, false)
        return binding.root

    }
    private var viewModel = ViewModelProvider(this)[AdsViewModel::class.java]


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFieldsChecking()
        setClickListeners()
        binding.btnAddPicture.setOnClickListener {
            picImage.launch(MIMETYPE_IMAGES)
        }
    }


    private fun checkFieldsBeforeActivatingButton() {
        if (etTitleName.text.isNotBlank() && etDescription.text.isNotBlank() &&
            etLocation.text.isNotBlank() && etPayment.text.isNotBlank()
            && iv_picture_placeholder.drawable != null
        ) {

            btn_add_ad.setBackgroundColor(requireContext().getColor(R.color.black))
            btn_add_ad.isClickable = true
        }
    }

    private fun setFieldsChecking() {
        etTitleName.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
        etDescription.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
        etLocation.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
        etPayment.doOnTextChanged { text, start, before, count ->
            checkFieldsBeforeActivatingButton()
        }
    }

    private fun setClickListeners() {
        btn_add_ad.setOnClickListener {
            val adEntity = AdEntity(
                title = etTitleName.text.toString(),
                description = etDescription.text.toString(),
                payment = etPayment.text.toString(),
                location = etLocation.text.toString(),
                image = btn_add_picture.toString()

            )
            viewModel.insertAd(adEntity)
        }
    }
}