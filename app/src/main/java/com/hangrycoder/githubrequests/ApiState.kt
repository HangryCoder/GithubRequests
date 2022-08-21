package com.hangrycoder.githubrequests

sealed class ApiState<out T> {
    object Loading : ApiState<Nothing>()
    class Success<out T>(val data: T) : ApiState<T>()
    object NetworkError : ApiState<Nothing>()
    object ServerError : ApiState<Nothing>()

}