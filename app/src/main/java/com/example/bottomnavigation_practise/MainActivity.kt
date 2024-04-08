package com.example.bottomnavigation_practise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigation_practise.screen.FirstFragment
import com.example.bottomnavigation_practise.screen.SecondFragment
import com.example.bottomnavigation_practise.screen.SplashFragment
import com.example.bottomnavigation_practise.screen.ThirdFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(SplashFragment())

        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> postDelayed { replaceFragment(FirstFragment()) }
                R.id.item_people -> postDelayed { replaceFragment(SecondFragment()) }
                R.id.item_settings -> postDelayed { replaceFragment(ThirdFragment()) }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Добавьте анимации перехода
        fragmentTransaction.setCustomAnimations(
            R.anim.from_right,  // анимация при входе нового фрагмента
            R.anim.to_left,  // анимация при выходе старого фрагмента
            R.anim.from_left,  // анимация при входе старого фрагмента (при нажатии кнопки "Назад")
            R.anim.to_right)  // анимация при выходе нового фрагмента (при нажатии кнопки "Назад")
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun postDelayed(action: () -> Unit) {
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).postDelayed({
            action.invoke()
        }, 300)
    }
}