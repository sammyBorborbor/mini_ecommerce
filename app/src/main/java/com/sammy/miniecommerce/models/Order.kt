package com.sammy.miniecommerce.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val orderNumber: String,
    val totalAmount: Double,
    val orderDate: String,
    val products: List<Product>,
    val status: String
): Parcelable
