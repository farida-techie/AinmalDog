package com.malkinfo.animaldog.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedferencesHelper {
    companion object {
        private const val PREF_TIME = "Pref time"
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharedferencesHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedferencesHelper =
            instance ?: synchronized(LOCK) {
                instance ?: buildHelper(context).also {
                    instance = it
                }
            }

        private fun buildHelper(context: Context): SharedferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedferencesHelper()
        }
    }
}

