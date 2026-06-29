package com.example.offlinecachesystem.domain.usecase

import com.example.offlinecachesystem.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
      operator fun invoke() =
        repository.getProducts()
}