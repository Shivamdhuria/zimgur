package com.example.zimgur.utils

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */
sealed class GenericResult<out T> {

    data class Success<out T>(val value: T) : GenericResult<T>()

    data class GenericError(val code: Int? = null, val errorMessage: String? = null) : GenericResult<Nothing>()

    object NetworkError : GenericResult<Nothing>()

    data class Progress(val loading: Boolean = true) : GenericResult<Nothing>()
}


