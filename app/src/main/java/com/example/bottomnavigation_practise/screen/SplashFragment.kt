package com.example.bottomnavigation_practise.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnavigation_practise.MainActivity
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.dictionary_list.FirstFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.visibility = View.GONE

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            (activity as? MainActivity)?.let { mainActivity ->

                mainActivity.supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.from_bottom, R.anim.to_top)
                    .replace(R.id.nav_host_fragment, FirstFragment())
                    .commit()

                mainActivity.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.visibility = View.VISIBLE
            }
        }

        return view
    }
}

