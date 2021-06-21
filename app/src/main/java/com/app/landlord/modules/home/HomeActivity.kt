package com.app.landlord.modules.home

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.landlord.R
import com.app.landlord.databinding.ActivityHomeBinding
import com.app.landlord.databinding.FragmentHelpSupportBinding
import com.app.landlord.modules.home.dashBoard.feedback.FeedBackFragment
import com.app.landlord.modules.home.dashBoard.home.HomeFragment
import com.app.landlord.modules.home.dashBoard.home.listener.BottomNavClickListenre
import com.app.landlord.modules.home.dashBoard.home.listener.TitleChangeListenre
import com.app.landlord.modules.home.dashBoard.maintenance.MaintenanceFragment
import com.app.landlord.modules.home.dashBoard.mangaProperty.ManagePropertyFragment
import com.app.landlord.modules.home.dashBoard.profile.ProfileFragment
import com.app.landlord.modules.home.dashBoard.requestAndProblems.RequestAndProblemsFragment
import com.app.landlord.modules.home.dashBoard.settings.SettingsFragment
import com.app.landlord.utils.FragConst
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.view.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    TitleChangeListenre, BottomNavClickListenre {
    var homeBinding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (
            homeBinding == null
        ) {
            homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        }
        setContentView(homeBinding!!.root)
        setUpDrawers()
        val fragment = HomeFragment()
        fragment.setOnTitlechangeListener(this)
        fragment.setOnBottomNavClickListener(this)
        replaceFragment(fragment)

        homeBinding!!.toolbarCancel.setOnClickListener {
            val intent = Intent(FragConst.LocalBroadCasst.CANCEL_CLICK)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }


    }

    private fun setUpDrawers() {
        setSupportActionBar(homeBinding!!.toolbar)

        val drawerToggle =
            ActionBarDrawerToggle(this, homeBinding!!.drawer, R.string.open, R.string.close)
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

    private var doubleBackToExitPressedOnce: Boolean = false
    override fun onBackPressed() {
//        if( homeBinding!!.drawer.isDrawerOpen(GravityCompat.START)){
//            homeBinding!!.drawer.closeDrawer(GravityCompat.START)
//        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            homeBinding!!.drawer.closeDrawer(GravityCompat.START)
            return
        }
        homeBinding!!.drawer.openDrawer(GravityCompat.START)
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        selectDrawer(item)
        return true
    }


    /* this method is used for switching the tabs on the clicks of the navigation drawer*/

    private fun selectDrawer(item: MenuItem) {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.home -> {
                homeBinding!!.toolbarCancel.visibility = View.GONE
                fragment = HomeFragment()
                fragment.setOnTitlechangeListener(this)
                fragment.setOnBottomNavClickListener(this)
//                homeBinding!!.toolbar.setTitle("")
                homeBinding!!.toolbarText.setText("Dashboard")
//                homeBinding!!.toolbar.setTitleTextColor(android.graphics.Color.BLACK)
//                setSupportActionBar( homeBinding!!.toolbar)
            }
            R.id.my_profile -> {
                homeBinding!!.toolbarCancel.visibility = View.GONE
                homeBinding!!.toolbarsearch.visibility = View.GONE
                fragment = ProfileFragment()
                homeBinding!!.toolbarText.setText("Profile")
            }
            R.id.manageProperty -> {
                homeBinding!!.toolbarCancel.visibility = View.GONE
                homeBinding!!.toolbarsearch.visibility = View.GONE
                fragment = ManagePropertyFragment()
                homeBinding!!.toolbarText.setText("Manager Property")
            }
            R.id.RequestAndProplems -> {
                homeBinding!!.toolbarCancel.visibility = View.GONE
                homeBinding!!.toolbarsearch.visibility = View.GONE
                fragment = RequestAndProblemsFragment()
                homeBinding!!.toolbarText.setText("Request And Problems")
            }
            R.id.Maintenance -> {
                homeBinding!!.toolbarCancel.visibility = View.GONE
                homeBinding!!.toolbarsearch.visibility = View.GONE
                fragment = MaintenanceFragment()
                homeBinding!!.toolbarText.setText("Maintenance")
            }
            R.id.Settings -> {
                homeBinding!!.toolbarCancel.visibility = View.GONE
                homeBinding!!.toolbarsearch.visibility = View.GONE
                fragment = SettingsFragment()
                homeBinding!!.toolbarText.setText("Settings")
            }
            R.id.Feedback -> {
                homeBinding!!.toolbarCancel.visibility = View.VISIBLE
                homeBinding!!.toolbarsearch.visibility = View.GONE
                fragment = FeedBackFragment()
                homeBinding!!.toolbarText.setText("Help & Support")
            }
            R.id.Signout -> {

            }

        }
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment?) {
        homeBinding!!.drawer.closeDrawers()
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }

    }

    override fun onTitleChange(title: String) {
        homeBinding!!.toolbarText.setText(title)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (homeBinding != null) {
            homeBinding = null
        }
    }

    override fun onBottonNavClick(visibility: Boolean) {
        Log.e("TAG", "onBottonNavClick: " + visibility)
        if (visibility) {
            homeBinding!!.toolbarsearch.visibility = View.VISIBLE
        } else
            homeBinding!!.toolbarsearch.visibility = View.GONE
    }
}