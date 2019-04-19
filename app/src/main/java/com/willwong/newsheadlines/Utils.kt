package com.willwong.newsheadlines

import android.icu.util.ULocale.getCountry
import android.icu.util.ULocale.getCountry
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.icu.util.ULocale.getCountry




class Utils {
    companion object {



    }

    fun getCountry(): String {
        val locale = Locale.getDefault().toString()
        val country = locale
        return country.toLowerCase()
    }
}