package com.example.pelmorexassessment.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.pelmorexassessment.BaseFragment
import com.example.pelmorexassessment.R
import com.example.pelmorexassessment.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BaseFragment() {

    private var _binding: FragmentGalleryBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<GalleryViewModel>()
    val adapter = GalleryListAdapter(false, this, object : GalleryActionListener {
        override fun onItemClick(position: Int) {
            viewModel.updateGalleryPosition(position)
            Navigation.findNavController(view!!)
                .navigate(R.id.gallery_to_full_screen, Bundle().apply {
                    putInt("position", position)
                })
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    fun initView() {
        binding.rvGallery.adapter = adapter
    }

    fun initObserver() {
        viewModel.gallery.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
    }
}