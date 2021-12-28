package com.sammy.miniecommerce.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

class ProductBinding {

    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }

        @BindingAdapter("loadImageFromDrawable")
        @JvmStatic
        fun loadImageDrawable(imageView: ImageView, imageUrl: Int) {
            imageView.setImageResource(imageUrl)
        }

    }
}