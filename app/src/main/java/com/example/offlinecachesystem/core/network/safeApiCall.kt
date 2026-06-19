/*
package com.example.offlinecachesystem.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
) : Flow<NetworkResult<T>> = flow {

    emit(NetworkResult.Loading)

    try {

        val response = apiCall()
        val code = response.code()

        when {
            response.isSuccessful -> {
                response.body()?.let { body ->
                    emit(
                        NetworkResult.Success(body)
                    )
                } ?: emit(
                    NetworkResult.Error(
                        NetworkError.Unknown(
                            code = code,
                            message = "Response body is null"
                        )
                    )
                )
            }

            code == 401 -> {
                emit(
                    NetworkResult.Error(
                        NetworkError.Unauthorized
                    )
                )
            }

            code == 403 -> {
                emit(
                    NetworkResult.Error(
                        NetworkError.Forbidden
                    )
                )
            }

            code == 404 -> {
                emit(
                    NetworkResult.Error(
                        NetworkError.NotFound
                    )
                )
            }

            code in 500..599 -> {
                emit(
                    NetworkResult.Error(
                        NetworkError.ServerError
                    )
                )
            }

            else -> {
                emit(
                    NetworkResult.Error(
                        NetworkError.Unknown(
                            code = code,
                            message = response.message()
                        )
                    )
                )
            }
        }

    } catch (e: UnknownHostException) {
        emit(
            NetworkResult.Error(
                NetworkError.NoInternet
            )
        )
    } catch (e: SocketTimeoutException) {
        emit(
            NetworkResult.Error(
                NetworkError.Timeout
            )
        )
    } catch (e: Exception) {
        emit(
            NetworkResult.Error(
                NetworkError.Unknown(
                    message = e.message ?: "Unknown Error"
                )
            )
        )
    }
}*/
