package com.sammy.miniecommerce.models


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Product(
    val description: String,
    val images: List<String>,
    val name: String,
    val new: Boolean,
    val price: Double,
    val variation: String
) : Parcelable