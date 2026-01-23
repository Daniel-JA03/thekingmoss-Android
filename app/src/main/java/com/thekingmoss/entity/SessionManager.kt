package com.thekingmoss.entity

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean =
        prefs.contains("token")

    fun getToken(): String =
        prefs.getString("token", "") ?: ""

    fun getUserId(): Long =
        prefs.getLong("user_id", -1L)

    fun clear() {
        prefs.edit().clear().apply()
    }
}