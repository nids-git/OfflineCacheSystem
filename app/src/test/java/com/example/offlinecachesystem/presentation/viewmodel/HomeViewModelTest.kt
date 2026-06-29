package com.example.offlinecachesystem.presentation.viewmodel

import app.cash.turbine.test
import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.domain.usecase.GetProductsUseCase
import com.example.offlinecachesystem.domain.usecase.SyncProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val getProductsUseCase: GetProductsUseCase = mockk()
    private val syncProductsUseCase: SyncProductsUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when syncProducts succeeds, uiState updates correctly`() = runTest(testDispatcher) {
        // Arrange
        coEvery { getProductsUseCase() } returns flowOf(emptyList())
        coEvery { syncProductsUseCase() } returns NetworkResult.Success(Unit)

        // Act
        val viewModel = HomeViewModel(
            getProductsUseCase,
            syncProductsUseCase
        )

        advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertTrue(state.products.isEmpty())
        assertTrue(state.errorMessage == null)
    }

}