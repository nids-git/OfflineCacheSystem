package com.example.offlinecachesystem.data.remote.datasource

import com.example.offlinecachesystem.data.local.dao.ProductDao
import com.example.offlinecachesystem.data.local.entity.ProductEntity
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {

     fun getProducts() =
        productDao.getProducts()

    suspend fun insertProducts(
        products: List<ProductEntity>
    ) {
        productDao.insertProducts(products)
    }

    suspend fun clearProducts() {
        productDao.deleteAllProducts()
    }
}