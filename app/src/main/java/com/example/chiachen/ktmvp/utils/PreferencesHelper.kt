package com.example.chiachen.ktmvp.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferencesHelper(private val context: Context) {
    fun saveLong(key: String, value: Long) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun loadLong(key: String): Long {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        return preferenceManager.getLong(key, 0L)
    }
}