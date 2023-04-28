package com.batcuevasoft.githubrepo.core.extensions

import java.text.DateFormat
import java.util.Date

fun Date.formatToDateString(): String {
    return DateFormat.getDateInstance().format(this)
}