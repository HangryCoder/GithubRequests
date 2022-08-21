package com.hangrycoder.githubrequests

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun convertTimestampToDate(timestamp: String?): String? {
        timestamp ?: return null

        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val output = SimpleDateFormat("dd LLL yy")

        var d: Date? = null
        return try {
            d = input.parse(timestamp)
            output.format(d)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
}