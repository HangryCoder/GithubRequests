package com.hangrycoder.githubrequests

sealed class ApiState<out T> {
    object Loading : ApiState<Nothing>()
    class Error(val error: NetworkResponse.NetworkError) : ApiState<Nothing>()
    class Success<out T>(val data: T) : ApiState<T>()
}