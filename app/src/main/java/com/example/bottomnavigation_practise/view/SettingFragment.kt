package com.example.bottomnavigation_practise.view


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavigation_practise.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale


class SettingFragment : Fragment() {
    fun restartApplication() {
        val intent = requireActivity().baseContext.packageManager.getLaunchIntentForPackage(requireActivity().baseContext.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        requireActivity().finish()
        startActivity(intent)
    }

    private lateinit var themeSwitcher: Switch
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        val cardViewSwitcher = view.findViewById<CardView>(R.id.cardView_switcher)
        val cardViewAbout = view.findViewById<CardView>(R.id.cardView_about)
        val cardViewSelectLanguage = view.findViewById<CardView>(R.id.cardView_select_language)
        val languageRadioGroup = view.findViewById<RadioGroup>(R.id.languageRadioGroup)
        val radioTj = view.findViewById<RadioButton>(R.id.radioTj)
        val radioRu = view.findViewById<RadioButton>(R.id.radioRu)
        val radioEng = view.findViewById<RadioButton>(R.id.radioEng)

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
        cardViewSelectLanguage.setOnClickListener {
            languageRadioGroup.visibility = View.VISIBLE
        }
        languageRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Снимаем галочку со всех радиокнопок
            radioTj.isChecked = false
            radioRu.isChecked = false
            radioEng.isChecked = false

            when (checkedId) {
                R.id.radioTj -> {
                    setLocale("tg", requireContext())
                    radioTj.isChecked = true // Помечаем выбранный язык галочкой
                }
                R.id.radioRu -> {
                    setLocale("ru", requireContext())
                    radioRu.isChecked = true // Помечаем выбранный язык галочкой
                }
                R.id.radioEng -> {
                    setLocale("en", requireContext())
                    radioEng.isChecked = true // Помечаем выбранный язык галочкой
                }
            }
        }

        return view
    }
    fun setLocale(languageCode: String, context: Context) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        // Сохранение выбранного языка в настройках приложения
        val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("language", languageCode)
        editor.apply()
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


