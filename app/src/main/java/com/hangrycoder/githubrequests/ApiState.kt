package com.hangrycoder.githubrequests

import okio.IOException

sealed class ApiState<out T> {
    object Loading : ApiState<Nothing>()
    class Success<out T>(val data: T) : ApiState<T>()
    class NetworkError(val error: IOException) : ApiState<IOException>()
    class ServerError<out T>(val error: T, val errorCode: Int) : ApiState<T>()
    class UnknownError(val error: Throwable?) : ApiState<Throwable>()

}