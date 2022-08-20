package com.hangrycoder.githubrequests

import okhttp3.Headers
import java.io.IOException
import java.net.UnknownHostException

sealed class NetworkResponse<out T : Any, out U : Any> {

    companion object {
        val GENERIC_ERROR_CODE = "E000"

        val NO_INTERNET_ERROR_MSG = "You seem to be offline"
        val GENERIC_ERROR_MSG = "Something went wrong"

        val NO_INTERNET_ERROR_MSG_DESC =
            "Please check your Wi-Fi connection or cellular data and try again"
        val GENERIC_ERROR_MSG_DESC =
            "There was an issue connecting with the server. Please try again"

    }

    data class ErrorData(
        var errorMessage: String = GENERIC_ERROR_MSG,
        var errorMessageDesc: String = GENERIC_ERROR_MSG_DESC,
        var errorCode: String = GENERIC_ERROR_CODE,
        var count: Int = 0
    )

    open var errorData = ErrorData()

    data class Success<T : Any>(
        val body: T,
        val headers: Headers? = null,
        val code: Int
    ) : NetworkResponse<T, Nothing>()

    data class ServerError<U : Any>(
        val body: U?,
        val code: Int,
        val headers: Headers? = null
    ) : NetworkResponse<Nothing, U>()

    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>() {
        override var errorData = when (error) {
            is UnknownHostException -> ErrorData(NO_INTERNET_ERROR_MSG, NO_INTERNET_ERROR_MSG_DESC)
            else -> ErrorData()
        }
    }

    data class UnknownError(
        val error: Throwable,
        val code: Int? = null,
        val headers: Headers? = null,
    ) : NetworkResponse<Nothing, Nothing>()
}