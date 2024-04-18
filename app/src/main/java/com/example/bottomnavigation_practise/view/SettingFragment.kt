package com.example.bottomnavigation_practise.view


import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavigation_practise.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

    private lateinit var themeSwitcher: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        val cardViewSwitcher = view.findViewById<CardView>(R.id.cardView_switcher)
        val cardViewAbout = view.findViewById<CardView>(R.id.cardView_about)
        themeSwitcher = view.findViewById(R.id.themeSwitcher)
        sharedPreferences =
            requireActivity().getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

        AppCompatDelegate.setDefaultNightMode(getCurrentTheme())
        themeSwitcher.isChecked = getCurrentTheme() == AppCompatDelegate.MODE_NIGHT_YES

        cardViewSwitcher.setOnClickListener {
            themeSwitcher.isChecked = !themeSwitcher.isChecked
            lifecycleScope.launch {
                delay(200)
                toggleTheme()
            }
        }

        themeSwitcher.setOnCheckedChangeListener { _, _ ->
            lifecycleScope.launch {
                delay(200)
                toggleTheme()
            }
        }

        cardViewAbout.setOnClickListener {
            val dialogView = LayoutInflater.from(context)
                .inflate(R.layout.alert_dialog_about, requireView() as ViewGroup, false)
            val buttonUnderstand = dialogView.findViewById<TextView>(R.id.buttonUnderstand)

            val alertDialog = AlertDialog.Builder(context)
                .setView(dialogView)
                .create()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            buttonUnderstand.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

        return view
    }

    private fun toggleTheme() {
        val currentTheme = getCurrentTheme()
        if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            saveTheme(AppCompatDelegate.MODE_NIGHT_NO)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            saveTheme(AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun getCurrentTheme(): Int {
        return sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    private fun saveTheme(themeMode: Int) {
        sharedPreferences.edit().putInt("theme_mode", themeMode).apply()
    }
}

