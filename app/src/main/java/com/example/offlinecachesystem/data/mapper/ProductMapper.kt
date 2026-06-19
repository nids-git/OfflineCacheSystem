package com.example.offlinecachesystem.data.mapper

import com.example.offlinecachesystem.data.remote.dto.ProductDto
import com.example.offlinecachesystem.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image
    )
}