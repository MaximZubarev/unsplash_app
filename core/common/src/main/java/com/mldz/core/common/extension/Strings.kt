package com.mldz.core.common.extension

import java.text.SimpleDateFormat
import java.util.Locale


fun String.formatFromServerToHuman(): String {
    return try {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val output = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val getAbbreviate = input.parse(this)
        getAbbreviate?.let {
            output.format(getAbbreviate)
        } ?: this
    } catch (e: Exception) {
        return this
    }
}