package com.app.landlord.modules.home

import android.R.attr
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.landlord.R
import com.app.landlord.databinding.ActivityHomeBinding
import com.app.landlord.modules.home.dashBoard.home.HomeFragment
import com.app.landlord.modules.home.dashBoard.home.listener.TitleChangeListenre
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.view.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,TitleChangeListenre {
    var homeBinding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (
            homeBinding == null
        ){
            homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        }
        setContentView(homeBinding!!.root)
        setUpDrawers()
val fragment = HomeFragment()
        fragment.setOnTitlechangeListener(this)
        replaceFragment(fragment)
    }

    private fun setUpDrawers() {
        setSupportActionBar(homeBinding!!.toolbar)

        val drawerToggle = ActionBarDrawerToggle(this,   homeBinding!!.drawer, R.string.open, R.string.close)
        homeBinding!!.drawer.addDrawerListener(drawerToggle)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerToggle.syncState()

        homeBinding!!.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                homeBinding!!.drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if( homeBinding!!.drawer.isDrawerOpen(GravityCompat.START)){
            homeBinding!!.drawer.closeDrawer(GravityCompat.START)
        }
        else
        {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
            super.onBackPressed()
        }
    }

    private var doubleBackToExitPressedOnce: Boolean = false
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            selectDrawer(item)
        return true
    }

    private fun selectDrawer(item: MenuItem) {

        var fragment : Fragment? = null
        when(item.itemId){
            R.id.home ->{
                    fragment = HomeFragment()
                    fragment.setOnTitlechangeListener(this)
//                homeBinding!!.toolbar.setTitle("")
                homeBinding!!.toolbarText.setText("Dashboard")
//                homeBinding!!.toolbar.setTitleTextColor(android.graphics.Color.BLACK)
//                setSupportActionBar( homeBinding!!.toolbar)
            }
            R.id.my_profile ->{

            }
            R.id.manageProperty ->{

            }
            R.id.RequestAndProplems ->{

            }
            R.id.Maintenance ->{

            }
            R.id.Settings ->{

            }
            R.id.Feedback ->{

            }
            R.id.Signout ->{

            }

        }
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment?) {
        homeBinding!!.drawer.closeDrawers()
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
        }

    }

    override fun onTitleChange(title: String) {
        homeBinding!!.toolbarText.setText(title)
    }


}