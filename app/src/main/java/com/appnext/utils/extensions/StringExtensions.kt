package com.appnext.utils.extensions

import java.util.*

fun String.capitaliseFullyUpperCasedString(): String {
    return this.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }
}