package com.example.offlinecachesystem.domain.repository

import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
     fun getProducts(): Flow<NetworkResult<List<Product>>>
}