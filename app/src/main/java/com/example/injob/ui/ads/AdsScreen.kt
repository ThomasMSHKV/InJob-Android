package com.example.injob.ui.ads

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.injob.R
import kotlinx.android.synthetic.main.ads_screen.*

class AdsScreen : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFieldsChecking()
    }

    private fun checkFieldsBeforeActivatingButton() {
        if (etCategoryName.text.isNotBlank() && etDescription.text.isNotBlank() &&
            etLocation.text.isNotBlank() && etPayment.text.isNotBlank()
            && iv_picture_placeholder.drawable != null) {

            btn_add_ad.setBackgroundColor(requireContext().getColor(R.color.black))
            btn_add_ad.isClickable = true
        }
    }

    private fun setFieldsChecking() {
        etCategoryName.doOnTextChanged { text, start, before, count ->
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
}