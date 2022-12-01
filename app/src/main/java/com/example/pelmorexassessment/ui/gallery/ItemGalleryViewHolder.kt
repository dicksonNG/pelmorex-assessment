package com.example.pelmorexassessment.ui.gallery

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.pelmorexassessment.databinding.ItemGalleryBinding

class ItemGalleryViewHolder(private val itemBinding: ItemGalleryBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        model: GalleryItemDisplayModel,
        position: Int,
        owner: LifecycleOwner,
        listener: GalleryActionListener,
        itemCount: Int
    ) {

//        val index = position.plus(1).toString() + "/" +itemCount
//        val displayModel = ImageDisplayModel(index,model.description,model.path)
        itemBinding.viewModel = model
        itemBinding.lifecycleOwner = owner

        itemBinding.root.setOnClickListener {
            listener.onItemClick(position)
        }

    }
}