package com.example.bottomnavigation_practise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigation_practise.view.HomeFragment
import com.example.bottomnavigation_practise.view.favorite.view.FavoriteFragment
import com.example.bottomnavigation_practise.view.SplashFragment
import com.example.bottomnavigation_practise.view.SettingFragment
import com.example.bottomnavigation_practise.view.victarina.view.VictarinaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> postDelayed { replaceFragment(HomeFragment()) }
                R.id.item_favorites -> postDelayed { replaceFragment(FavoriteFragment()) }
                R.id.item_victorina-> postDelayed { replaceFragment(VictarinaFragment()) }
                R.id.item_settings -> postDelayed { replaceFragment(SettingFragment()) }
            }
            true
        }

        if (savedInstanceState == null){
            replaceFragment(SplashFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.commit()
    }

    private fun postDelayed(action: () -> Unit) {
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).postDelayed({
            action.invoke()
        }, 300)
    }
}