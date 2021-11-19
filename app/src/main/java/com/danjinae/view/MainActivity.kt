package com.danjinae.view

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.danjinae.R
import com.danjinae.view.car.CarFragment
import com.danjinae.view.community.CommunityFragment
import com.danjinae.view.complain.ComplaintFragment
import com.danjinae.view.fee.ManagementFeeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val frame_l: FrameLayout by lazy {
        findViewById(R.id.frame_l)
    }

    private val bn_navi: BottomNavigationView by lazy {
        findViewById(R.id.bn_navi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(frame_l.id, HomeFragment()).commit()

        bn_navi.setOnNavigationItemSelectedListener{
            replaceFragment(
                when (it.itemId){
                    R.id.menu_car -> CarFragment()
                    R.id.menu_community -> CommunityFragment()
                    R.id.menu_home -> HomeFragment()
                    R.id.menu_management_fee -> ManagementFeeFragment()
                    else -> ComplaintFragment()
                }

            )
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(frame_l.id, fragment).commit()
    }

}