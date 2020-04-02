package com.example.zimgur.extensions

import com.example.zimgur.utils.ApiResult
import com.example.zimgur.utils.CacheResult
import com.example.zimgur.utils.Constants.Companion.CACHE_TIMEOUT
import com.example.zimgur.utils.Constants.Companion.NETWORK_TIMEOUT
import com.example.zimgur.utils.ErrorHandling.Companion.CACHE_ERROR_TIMEOUT
import com.example.zimgur.utils.ErrorHandling.Companion.NETWORK_ERROR_TIMEOUT
import com.example.zimgur.utils.ErrorHandling.Companion.UNKNOWN_ERROR
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */
private val TAG: String = "AppDebug"

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T?): ApiResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(NETWORK_TIMEOUT) {
                ApiResult.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    ApiResult.GenericError(code, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    ApiResult.NetworkError
                }

                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ApiResult.GenericError(code, errorResponse)
                }

                else -> {
                    ApiResult.GenericError(null, UNKNOWN_ERROR)
                }
            }

        }
    }
}

suspend fun <T> safeCacheCall(dispatcher: CoroutineDispatcher, cacheCall: suspend () -> T?): CacheResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(CACHE_TIMEOUT) {
                CacheResult.Success(cacheCall.invoke())
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutCancellationException -> {
                    CacheResult.GenericError(CACHE_ERROR_TIMEOUT)
                }
                else -> {
                    CacheResult.GenericError(UNKNOWN_ERROR)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        UNKNOWN_ERROR
    }
}
