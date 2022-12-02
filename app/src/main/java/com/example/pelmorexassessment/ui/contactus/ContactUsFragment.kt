package com.example.pelmorexassessment.ui.contactus

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.pelmorexassessment.BaseFragment
import com.example.pelmorexassessment.R
import com.example.pelmorexassessment.databinding.FragmentContactusBinding
import com.example.pelmorexassessment.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ContactUsFragment : BaseFragment() {
    private var _binding: FragmentContactusBinding? = null
    private val binding get() = _binding!!

    val contactUsViewModel by viewModels<ContactUsViewModel>()
    override fun provideBaseViewModel()=contactUsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactusBinding.inflate(inflater, container, false)
        binding.viewModel = contactUsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    fun initListener() {
        binding.tvSend.setOnClickListener {
            showDialog(
                requireContext().getString(R.string.title_contact_us),
                requireContext().getString(R.string.contact_us_submit_success)
            )
        }

        binding.tvName.inputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(str: Editable?) {
                contactUsViewModel.setName(str.toString())
            }
        })
        binding.tvEmail.inputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(str: Editable?) {
                contactUsViewModel.setEmail(str.toString())
            }
        })

        binding.tvPhoneNumber.inputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(str: Editable?) {
                contactUsViewModel.setPhoneNumber(str.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}