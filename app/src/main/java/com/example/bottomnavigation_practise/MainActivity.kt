package com.example.bottomnavigation_practise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigation_practise.screen.FirstFragment
import com.example.bottomnavigation_practise.screen.SecondFragment
import com.example.bottomnavigation_practise.screen.ThirdFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item_layout)
        replaceFragment(FirstFragment())

        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> replaceFragment(FirstFragment())
                R.id.item_people -> replaceFragment(SecondFragment())
                R.id.item_settings -> replaceFragment(ThirdFragment())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
           .replace(R.id.nav_host_fragment, fragment)
           .commit()
    }
}
