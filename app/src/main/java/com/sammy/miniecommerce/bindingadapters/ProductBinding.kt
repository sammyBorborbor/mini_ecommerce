package com.sammy.miniecommerce.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.sammy.miniecommerce.R

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

        @BindingAdapter("applyStatusColor")
        @JvmStatic
        fun applyStatusColor(view: View, status: String) {
            when (status) {
                "pending" -> {
                    view.setBackgroundResource(R.drawable.pending_bg)
                }
                "in progress" -> {
                    view.setBackgroundResource(R.drawable.progress_bg)
                }
                else -> {
                    view.setBackgroundResource(R.drawable.success_bg)
                }
            }
        }

    }
}