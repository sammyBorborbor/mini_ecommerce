package com.sammy.miniecommerce.ui.fragments.newarrivals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sammy.miniecommerce.data.SampleData
import com.sammy.miniecommerce.models.Product

class NewArrivalsViewModel : ViewModel() {

    fun getNewArrivals(): MutableLiveData<List<Product>> {
        return SampleData.loadNewProducts()
    }
}