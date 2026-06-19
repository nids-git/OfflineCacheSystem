package com.example.offlinecachesystem.di

import com.example.offlinecachesystem.data.repository.ProductRepositoryImpl
import com.example.offlinecachesystem.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository
}