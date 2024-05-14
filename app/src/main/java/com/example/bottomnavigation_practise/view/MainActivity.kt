package com.example.bottomnavigation_practise.view

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.view.home.view.HomeFragment
import com.example.bottomnavigation_practise.view.setting.SettingFragment
import com.example.bottomnavigation_practise.view.splash.SplashFragment
import com.example.bottomnavigation_practise.view.favorite.view.FavoriteFragment
import com.example.bottomnavigation_practise.view.victarina.view.VictarinaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.util.Locale



class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private val bottomNavigationView by lazy { findViewById<BottomNavigationView>(R.id.bottom_navigation_view) }
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences =
            this.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        AppCompatDelegate.setDefaultNightMode(getCurrentTheme())

        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedLanguage = prefs.getString("language", "en")
        if (savedLanguage != null) {
            setLocale(savedLanguage)
        }

        bottomNavigationView.setOnItemSelectedListener(this)

        if (savedInstanceState == null) {
            replaceFragment(SplashFragment())
        }
    }

    override fun onBackPressed() {
        if(bottomNavigationView.selectedItemId != R.id.item_home){
            bottomNavigationView.menu[0].isChecked = true
            onNavigationItemSelected(bottomNavigationView.menu[0])
            return

        }
        super.onBackPressed()
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
       return true
    }

    private fun getCurrentTheme(): Int {
        return sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

}