package com.example.offlinecachesystem.domain.usecase

import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.domain.repository.ProductRepository
import javax.inject.Inject

class SyncProductsUseCase @Inject constructor(
    private val repository: ProductRepository

) {

    suspend operator fun invoke(): NetworkResult<Unit> {
        return repository.syncProducts()
    }
}