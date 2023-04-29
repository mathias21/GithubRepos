package com.batcuevasoft.githubrepo.data.remote

import retrofit2.HttpException
import timber.log.Timber

suspend fun <T> tryNetworkSuspending(block: suspend () -> T): NetworkResponse<T> {
    return try {
        NetworkResponse.Success(block())
    } catch (e: HttpException) {
        networkErrorForException(e)
    } catch (e: Exception) {
        Timber.d(e)
        NetworkResponse.Error.ServerError(e)
    }
}

private fun networkErrorForException(e: HttpException): NetworkResponse.Error {
    return when (e.code()) {
        400 -> NetworkResponse.Error.BadRequest(e)
        401 -> NetworkResponse.Error.Unauthorised(e)
        404 -> NetworkResponse.Error.NotFound(e)
        500 -> NetworkResponse.Error.ServerError(e)
        else -> NetworkResponse.Error.ServerError(e)
    }
}