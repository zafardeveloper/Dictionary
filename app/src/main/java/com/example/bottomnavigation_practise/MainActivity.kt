package com.example.bottomnavigation_practise

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.bottomnavigation_practise.view.HomeFragment
import com.example.bottomnavigation_practise.view.SettingFragment
import com.example.bottomnavigation_practise.view.SplashFragment
import com.example.bottomnavigation_practise.view.favorite.view.FavoriteFragment
import com.example.bottomnavigation_practise.view.victarina.view.VictarinaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var languageManager: LanguageManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    replaceFragment(HomeFragment())
                }

                R.id.item_favorites -> {
                    replaceFragment(FavoriteFragment())
                }
                R.id.item_victorina -> {
                    replaceFragment(VictarinaFragment())
                }
                R.id.item_settings -> {
                    replaceFragment(SettingFragment())
                }
            }
            true
        }

        if (savedInstanceState == null) {
            replaceFragment(SplashFragment())
        }
        languageManager = LanguageManager(this)
        val selectedLanguage = languageManager.getLanguage()
        setLocale(selectedLanguage)
    }
    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }


    private fun changeLanguage(language: String) {
        languageManager.saveLanguage(language)
        setLocale(language)
        recreate()
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.commit()
    }

}