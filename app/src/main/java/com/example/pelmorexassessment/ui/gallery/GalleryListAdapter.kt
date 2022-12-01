package com.example.pelmorexassessment.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pelmorexassessment.R

class GalleryListAdapter(
    val isFullScreen: Boolean,
    private val owner: LifecycleOwner,
    private val listener: GalleryActionListener
) :
    ListAdapter<GalleryItemDisplayModel, RecyclerView.ViewHolder>(GalleryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (isFullScreen) {
            ItemFullScreenGalleryViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_gallery_full_screen,
                    parent,
                    false
                )
            )
        } else {
            ItemGalleryViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_gallery,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemFullScreenGalleryViewHolder -> {
                holder.bind(getItem(position), owner)
            }
            is ItemGalleryViewHolder -> {
                holder.bind(getItem(position), position, owner, listener, itemCount)
            }
        }
    }
}

class GalleryDiffCallBack : DiffUtil.ItemCallback<GalleryItemDisplayModel>() {

    override fun areContentsTheSame(
        oldItem: GalleryItemDisplayModel,
        newItem: GalleryItemDisplayModel
    ): Boolean {
        return oldItem.path == newItem.path
    }

    override fun areItemsTheSame(
        oldItem: GalleryItemDisplayModel,
        newItem: GalleryItemDisplayModel
    ): Boolean {
        return ((oldItem.description == newItem.description) &&
                (oldItem.path == newItem.path) &&
                (oldItem.type == newItem.type))
    }
}