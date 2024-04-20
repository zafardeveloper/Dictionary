package com.example.bottomnavigation_practise
import android.content.Context
import android.content.SharedPreferences
class LanguageManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE)

    fun saveLanguage(language: String) {
        val editor = prefs.edit()
        editor.putString("selectedLanguage", language)
        editor.apply()
    }

    fun getLanguage(): String {
        return prefs.getString("selectedLanguage", "tj") ?: "tj"
    }
}
