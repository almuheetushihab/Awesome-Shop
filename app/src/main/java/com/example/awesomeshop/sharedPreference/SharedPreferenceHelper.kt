package com.example.awesomeshop.sharedPreference

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveCredentials(username: String, password: String, fullName: String) {
        val editor = sharedPreferences.edit()
        editor.putString("fullName", fullName)
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }
    fun getFullName(): String? {
        return sharedPreferences.getString("fullName", null)
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    fun getPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

    fun clearCredentials() {
        sharedPreferences.edit().clear().apply()
    }
}
