package com.sammy.miniecommerce.data

import androidx.lifecycle.MutableLiveData
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.models.Order
import com.sammy.miniecommerce.models.Product
import com.sammy.miniecommerce.models.ProductCategory

class SampleData {

    companion object {

        fun loadNewProducts(): MutableLiveData<List<Product>> {
            val productList: MutableLiveData<List<Product>> = MutableLiveData()
            val product = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = true,
                images = listOf("https://source.unsplash.com/random/300×300/?fashion"),
                variation = ""
            )
            val product1 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = true,
                images = listOf("https://source.unsplash.com/random/300×300/?bags"),
                variation = ""
            )
            val product2 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = true,
                images = listOf("https://source.unsplash.com/random/300×300/?shoes"),
                variation = ""
            )
            productList.value = (listOf(product, product1, product2))

            return productList

        }

        fun loadProducts(): MutableLiveData<List<Product>> {
            val productList: MutableLiveData<List<Product>> = MutableLiveData()
            val product = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = false,
                images = listOf("https://image.freepik.com/free-photo/women-bag_1159-798.jpg"),
                variation = ""
            )
            val product1 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = true,
                images = listOf("https://image.freepik.com/free-photo/pair-trainers_144627-3799.jpg"),
                variation = ""
            )
            val product2 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = false,
                images = listOf("https://image.freepik.com/free-photo/shoes_1303-3601.jpg"),
                variation = ""
            )
            val product3 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = false,
                images = listOf("https://image.freepik.com/free-photo/beautiful-luxury-necklace-jewelry-stand-neck_1339-7950.jpg"),
                variation = ""
            )
            val product4 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = true,
                images = listOf("https://image.freepik.com/free-psd/simple-black-men-s-tee-mockup_53876-57893.jpg"),
                variation = ""
            )
            val product5 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = false,
                images = listOf("https://image.freepik.com/free-photo/fashion-shoes-sneakers_1203-7529.jpg"),
                variation = ""
            )
            val product6 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = false,
                images = listOf("https://image.freepik.com/free-photo/color-purple-elegance-woman-luxury_1203-6518.jpg"),
                variation = ""
            )
            val product7 = Product(
                name = "Cat Tee Black T-Shirt",
                price = 20.00,
                description = "4 MSL",
                new = false,
                images = listOf("https://image.freepik.com/free-psd/floral-bagpack-mock-up_1310-136.jpg"),
                variation = ""
            )
            productList.value = (listOf(product, product1, product2, product3, product4, product5, product6, product7))

            return productList

        }

        fun loadCategories(): MutableLiveData<List<ProductCategory>> {
            val categoryList: MutableLiveData<List<ProductCategory>> = MutableLiveData()
//            val c1 = ProductCategory(name = "Shoes", image = "https://image.freepik.com/free-photo/derby-shoes-men-formal-wear_53876-96609.jpg")
//            val c2 = ProductCategory(name = "Shirts", image = "https://www.svgrepo.com/show/40246/shirt.svg")
            val c1 = ProductCategory(name = "Shirts", image = R.drawable.ic_shirt)
            val c2 = ProductCategory(name = "Shoes", image = R.drawable.ic_shoes)
            val c3 = ProductCategory(name = "Dress", image = R.drawable.ic_dress)
            val c4 = ProductCategory(name = "Bags", image = R.drawable.ic_bag)
            val c5 = ProductCategory(name = "Glasses", image = R.drawable.ic_glasses)
            val c6 = ProductCategory(name = "Necklaces", image = R.drawable.ic_necklace)

            categoryList.value = listOf(c1, c2, c3, c4, c5, c6)
            return categoryList
        }

        fun loadOrders(): MutableLiveData<List<Order>> {
            val orderList: MutableLiveData<List<Order>> = MutableLiveData()
            val order1 = Order(orderNumber = "#00012345", orderDate = "12 Dec 2021", totalAmount = 250.00,
            products = loadProducts().value!!.take(5), status = "pending")

            val order2 = Order(orderNumber = "#00012378", orderDate = "18 Dec 2021", totalAmount = 250.00,
                products = loadProducts().value!!.takeLast(3), status = "in progress")

            val order3 = Order(orderNumber = "#00012367", orderDate = "22 Dec 2021", totalAmount = 250.00,
                products = loadProducts().value!!.take(2), status = "completed")

            orderList.value = listOf(order1, order2, order3)
            return orderList
        }


    }




}