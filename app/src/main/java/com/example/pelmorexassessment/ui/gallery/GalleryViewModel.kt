package com.example.pelmorexassessment.ui.gallery

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pelmorexassessment.R
import com.example.pelmorexassessment.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor() : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    private val _imageGallery = MutableLiveData<List<GalleryItemDisplayModel>>().apply {
        val listOfImage = listOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8,
            R.drawable.image_9,
            R.drawable.image_10,
        )
        value = listOfImage.map {
            GalleryItemDisplayModel(
                type = ComponentType.IMAGE_ITEM,
                path = it
            )
        }
    }
    private val _galleryPos = MutableLiveData<Int>(0)
    val galleryPos: LiveData<Int> get() = _galleryPos

    val gallery: LiveData<List<GalleryItemDisplayModel>> = _imageGallery

    val text: LiveData<String> = _text

    fun updateGalleryPosition(index: Int) {
        _galleryPos.postValue(index)
    }

    fun getGalleryPosition(): Int? {
        return _galleryPos.value
    }
}