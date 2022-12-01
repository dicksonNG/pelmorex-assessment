package com.example.pelmorexassessment

import androidx.fragment.app.Fragment
import com.example.pelmorexassessment.ui.dialog.GeneralDialog

abstract class BaseFragment : Fragment() {

    fun showDialog(title: String, message: String) {
        GeneralDialog.show(
            parentFragmentManager,
            title,
            message,
            onEnter = {
            },
            showSuccess = false
        )
    }
}