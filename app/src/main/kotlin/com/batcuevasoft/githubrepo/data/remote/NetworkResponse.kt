package com.batcuevasoft.githubrepo.data.remote

sealed class NetworkResponse<out T> {
    sealed class Error(open val exception: Exception) : NetworkResponse<Nothing>() {
        data class Unauthorised(override val exception: Exception = Exception("Unauthorised")) : Error(exception)
        data class ServerError(override val exception: Exception) : Error(exception)
        data class UnknownNetworkError(override val exception: Exception) : Error(exception)
        object NullBodyError : Error(NullPointerException("Null error body"))
        data class NotFound(override val exception: Exception) : Error(exception)
        data class BadRequest(override val exception: Exception) : Error(exception)
    }

    data class Success<T>(val data: T) : NetworkResponse<T>()
}