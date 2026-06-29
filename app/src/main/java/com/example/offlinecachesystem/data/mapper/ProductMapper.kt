package com.example.offlinecachesystem.data.mapper

import com.example.offlinecachesystem.data.local.entity.ProductEntity
import com.example.offlinecachesystem.data.remote.dto.ProductDto
import com.example.offlinecachesystem.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image
    )
}

fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        image = image,
        description = description,
        category = category
    )
}