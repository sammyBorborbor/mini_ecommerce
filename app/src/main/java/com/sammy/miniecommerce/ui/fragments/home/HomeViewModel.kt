package com.sammy.miniecommerce.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sammy.miniecommerce.data.SampleData
import com.sammy.miniecommerce.models.Order
import com.sammy.miniecommerce.models.Product
import com.sammy.miniecommerce.models.ProductCategory

class HomeViewModel : ViewModel() {
    fun getNewArrivals(): MutableLiveData<List<Product>> {
        return SampleData.loadNewProducts()
    }

    fun getProducts(): MutableLiveData<List<Product>> {
        return SampleData.loadProducts()
    }

    fun getCategories(): MutableLiveData<List<ProductCategory>> {
        return SampleData.loadCategories()
    }

    fun getOrders(): MutableLiveData<List<Order>> {
        return SampleData.loadOrders()
    }
}