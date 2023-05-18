package com.example.kotlinTeam.common.sharedPrefs

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    companion object {
        private const val IS_ONBOARDING_REQUIRED_KEY = "IS_ONBOARDING_REQUIRED"
        private const val PREFS_NAME = "kotlinTeamSharedPref"
    }

    private var sharedPrefs: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPrefs.edit()
    }

    fun putIsOnboardingRequired(isOnboardingRequired: Boolean) {
        editor.putBoolean(IS_ONBOARDING_REQUIRED_KEY, isOnboardingRequired).apply()
    }

    fun getIsOnboardingRequired(): Boolean {
        return  sharedPrefs.getBoolean(IS_ONBOARDING_REQUIRED_KEY, true)
    }

    fun clear() {
        editor.clear()
            .apply()
    }
}