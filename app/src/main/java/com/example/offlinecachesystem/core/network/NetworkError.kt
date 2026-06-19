package com.example.offlinecachesystem.core.network

sealed interface NetworkError {

    data object NoInternet : NetworkError
    data object Timeout : NetworkError
    data object Unauthorized : NetworkError

    data object Forbidden : NetworkError
    data object NotFound : NetworkError
    data object ServerError : NetworkError
    data class Unknown(
        val code: Int? = null,
        val message: String
    ) : NetworkError
}