package com.example.testapp.common.extension

import android.os.Build
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
object DateExt {
    fun formatDate(dateString: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
            val date = OffsetDateTime.parse(dateString, inputFormatter)
            return date.format(outputFormatter)
        } else {
            return formatDateOld(dateString)
        }
    }

    private fun formatDateOld(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date!!)
    }
}