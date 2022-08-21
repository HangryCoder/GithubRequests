package com.hangrycoder.githubrequests

import okio.IOException
import retrofit2.HttpException

//Might need to delete this
sealed class ApiState<out T> {
    object Loading : ApiState<Nothing>()
    class Success<out T>(val data: T) : ApiState<T>()
    class NetworkError(val error: IOException) : ApiState<IOException>()
    class ServerError(val error: HttpException) : ApiState<Nothing>()
    class UnknownError(val error: Exception) : ApiState<Nothing>()

}