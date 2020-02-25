package com.anirudh.tripmonitor.commons

import android.content.Context

open class SharedPref {

    open fun saveSharedPrefBool(context: Context, name: String, boolean: Boolean) {
        val pref = context.getSharedPreferences("TripMonitor", 0)
        val editor = pref.edit()
        editor.putBoolean(name, boolean)
        editor.apply()
    }

    open fun getSharedPrefBool(context: Context, name: String): Boolean {
        val pref = context.getSharedPreferences("TripMonitor", 0)
        return pref.getBoolean(name, false)
    }
}