package com.mldz.core.network.models.utils

import retrofit2.HttpException
import retrofit2.Response


sealed class NetworkResult<out Any> {
    class Success<T: Any>(val data: T): NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?): NetworkResult<T>()
    class Exception<T: Any>(val throwable: Throwable): NetworkResult<T>()
}

suspend fun <T: Any, R: Any> handleApi(
    transform: (T) -> R,
    execute: suspend() -> Response<T>
): NetworkResult<R> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(data = transform(body))
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (t: Throwable) {
        t.printStackTrace()
        NetworkResult.Exception(throwable = t)
    }
}