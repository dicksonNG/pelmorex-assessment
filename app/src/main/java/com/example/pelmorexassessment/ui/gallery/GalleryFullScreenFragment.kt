package com.example.pelmorexassessment.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.pelmorexassessment.BaseFragment
import com.example.pelmorexassessment.R
import com.example.pelmorexassessment.databinding.FragmentGalleryFullScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFullScreenFragment : BaseFragment() {
    private var _binding: FragmentGalleryFullScreenBinding? = null
    private val viewModel by activityViewModels<GalleryViewModel>()
    val adapter = GalleryListAdapter(true, this, object : GalleryActionListener {
        override fun onItemClick(position: Int) {

        }
    })
    override fun provideBaseViewModel()=viewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryFullScreenBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    fun initView() {
        binding.vpGallery.adapter = adapter
    }

    fun initListener() {
        binding.ivClose.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
        binding.vpGallery.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.updateGalleryPosition(position)
            }
        })
    }

    fun initObserver() {
        viewLifecycleOwner.let {
            viewModel.gallery.observe(it) {
                adapter.submitList(it)
                val itemCount = adapter.itemCount
                binding.itemCount = itemCount
                val pos = arguments?.getInt("position") ?: 0
                binding.vpGallery.setCurrentItem(pos, false)
            }
        }
    }
}