package com.example.offlinecachesystem.data.remote.datasource

import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.data.remote.BaseRemoteDataSource
import com.example.offlinecachesystem.data.remote.api.ProductApi
import com.example.offlinecachesystem.data.remote.dto.ProductDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val api: ProductApi

) : BaseRemoteDataSource() {

     fun getProducts() : Flow<NetworkResult<List<ProductDto>>> {
        return safeApiCall {
            api.getProducts()
        }
     }

}