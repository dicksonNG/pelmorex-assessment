package com.example.pelmorexassessment.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import coil.request.ErrorResult

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import java.util.Base64

@BindingAdapter("imageBase64")
fun deCodeBase64(view: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }

    val decodedString: ByteArray = android.util.Base64.decode(url,0)
    val decodedByte: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    view.load(decodedByte) {
    }
}

@BindingAdapter("imageSrc")
fun setImageDrawable(view: ImageView, drawableResId: Int?) {
    if (drawableResId == 0 || drawableResId == null) return
    view.load(drawableResId) {
        crossfade(true)
    }
}