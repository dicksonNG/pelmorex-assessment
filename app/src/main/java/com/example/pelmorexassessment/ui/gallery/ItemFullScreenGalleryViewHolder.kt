package com.example.pelmorexassessment.ui.gallery

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.pelmorexassessment.databinding.ItemGalleryFullScreenBinding

class ItemFullScreenGalleryViewHolder(private val itemBinding: ItemGalleryFullScreenBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        model: GalleryItemDisplayModel,
        owner: LifecycleOwner,
    ) {
        itemBinding.viewModel = model
        itemBinding.lifecycleOwner = owner
    }
}