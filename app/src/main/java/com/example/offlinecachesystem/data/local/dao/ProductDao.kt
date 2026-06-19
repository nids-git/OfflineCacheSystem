package com.example.offlinecachesystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.offlinecachesystem.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(
        products: List<ProductEntity>
    )

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductEntity>

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}