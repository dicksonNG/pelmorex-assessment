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

@BindingAdapter(value = ["backgroundRes"])
fun backgroundResources(view: View, resId: Int?) {
    if (resId != null && resId > 0) {
        view.setBackgroundResource(resId)
    } else {
        view.background = null
    }
}

@BindingAdapter(value = ["backgroundColorString"])
fun backgroundColorString(view: View, colorString: String?) {
    if (colorString != null) {
        try {
            view.setBackgroundColor(Color.parseColor(colorString))
        } catch (e: Exception) {
            view.background = null
        }
    } else {
        view.background = null
    }
}

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
//    view.setImageDrawable(ContextCompat.getDrawable(view.context, drawableResId))
}


@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    if (url.isNullOrEmpty()) return

    view.load(url) {

    }
}

@BindingAdapter("imageUrlCircle")
fun setImageUrlCircle(view: ImageView, url: String?) {
    if (url.isNullOrEmpty()) return

    view.load(url) {
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("imageUrlRes", "imagePlaceHolderRes", "imageErrorRes", requireAll = false)
fun setImageUrlWithRes(
    view: ImageView,
    url: String?,
    imagePlaceHolderRes: Drawable?,
    imageErrorRes: Drawable?
) {
    if (url.isNullOrEmpty() && imageErrorRes == null) return

    view.load(url) {
        placeholder(imagePlaceHolderRes)
        error(imageErrorRes)
        listener(object : ImageRequest.Listener {
            override fun onError(request: ImageRequest, result: ErrorResult) {
                super.onError(request, result)
            }
        })
    }
}