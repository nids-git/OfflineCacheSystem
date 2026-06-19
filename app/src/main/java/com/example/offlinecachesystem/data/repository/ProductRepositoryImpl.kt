package com.example.offlinecachesystem.data.repository

import android.util.Log
import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.data.mapper.toDomain
import com.example.offlinecachesystem.data.remote.datasource.ProductRemoteDataSource
import com.example.offlinecachesystem.domain.model.Product
import com.example.offlinecachesystem.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override fun getProducts(): Flow<NetworkResult<List<Product>>> {

        return remoteDataSource.getProducts()
            .map { result ->

                when(result) {

                    is NetworkResult.Loading -> {
                        Log.d("ProductRepositoryImpl", "getProducts: Loading")
                        NetworkResult.Loading
                    }

                    is NetworkResult.Error -> {
                        Log.e("ProductRepositoryImpl", "getProducts: Error - ${result.error}")
                        NetworkResult.Error(result.error)
                    }

                    is NetworkResult.Success -> {
                        Log.d("ProductRepositoryImpl", "getProducts: Success - ${result.data.size} items")
                        NetworkResult.Success(
                            result.data.map { dto ->
                                dto.toDomain()
                            }
                        )
                    }
                }
            }
    }
}