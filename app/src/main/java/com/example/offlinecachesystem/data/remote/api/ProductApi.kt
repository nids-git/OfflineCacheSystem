package com.example.offlinecachesystem.data.remote.api

import com.example.offlinecachesystem.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): Response<List<ProductDto>>

}