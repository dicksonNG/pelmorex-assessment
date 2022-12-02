package com.example.pelmorexassessment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pelmorexassessment.base.BaseViewModel
import com.example.pelmorexassessment.ui.dialog.GeneralDialog

abstract class BaseFragment : Fragment() {
    abstract fun provideBaseViewModel(): BaseViewModel?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideBaseViewModel()?.let { observeCommonViewState(it) }
    }

    fun observeCommonViewState(vm: BaseViewModel) {
        vm.errorDialogAction.observe(viewLifecycleOwner) {
            showDialog(
                it.title ?: "",
                it.message
            )
        }
        vm.showLoading.observe(viewLifecycleOwner) {
            if(requireActivity() is MainActivity){
                (requireActivity() as MainActivity).showLoadingDialog(it)
            }
        }
    }

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