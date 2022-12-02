package com.example.pelmorexassessment

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object{
        val NORMAL_FORMAT = SimpleDateFormat("EEE MMM d HH:mm aa", Locale.ENGLISH)

        fun getDateTime(s: String): String? {
            try {
                val sdf = NORMAL_FORMAT
                val netDate = Date(s.toLong())
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }

    }
}