package com.example.offlinecachesystem.data.repository

import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.data.mapper.toDomain
import com.example.offlinecachesystem.data.mapper.toEntity
import com.example.offlinecachesystem.data.remote.datasource.ProductLocalDataSource
import com.example.offlinecachesystem.data.remote.datasource.ProductRemoteDataSource
import com.example.offlinecachesystem.domain.model.Product
import com.example.offlinecachesystem.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.offlinecachesystem.di.IoDispatchers

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource,
    @IoDispatchers private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override  fun getProducts(): Flow<List<Product>> {
        return localDataSource.getProducts()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }
    override suspend fun syncProducts(): NetworkResult<Unit>  =

        withContext(ioDispatcher){
            try {
                var syncResult: NetworkResult<Unit> =
                    NetworkResult.Success(Unit)

                remoteDataSource.getProducts()
                    .collect { result ->

                        when(result) {

                            is NetworkResult.Success -> {

                                localDataSource.clearProducts()

                                localDataSource.insertProducts(
                                    result.data.map {
                                        it.toEntity()
                                    }
                                )

                                syncResult =
                                    NetworkResult.Success(Unit)
                            }

                            is NetworkResult.Error -> {
                                syncResult =
                                    NetworkResult.Error(result.error)
                            }

                            is NetworkResult.Loading -> {
                            }
                        }
                    }

                syncResult

            } catch (e: Exception) {

                NetworkResult.Error(
                    com.example.offlinecachesystem.core.network.NetworkError.Unknown(
                        message = e.message ?: "Unknown error"
                    )
                )
            }
    }
}