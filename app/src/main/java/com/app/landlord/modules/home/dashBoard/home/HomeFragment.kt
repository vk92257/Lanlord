package com.app.landlord.modules.home.dashBoard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.landlord.R
import com.app.landlord.databinding.FragmentHomeBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.messages.MessagesFragment
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.NoticesFragment
import com.app.landlord.modules.home.dashBoard.home.fragments.notifications.NotificationFragment
import com.app.landlord.modules.home.dashBoard.home.fragments.tenants.TenantsFragment
import com.app.landlord.modules.home.dashBoard.home.listener.BottomNavClickListenre
import com.app.landlord.modules.home.dashBoard.home.listener.TitleChangeListenre
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    private val TAG: String? = "HomeFragment"
    var titleChangeListenre: TitleChangeListenre?= null
    var navClickListenre: BottomNavClickListenre?= null
    var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()
        binding!!.bottomNavigationView.setItemIconTintList(null)
        binding!!.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        replaceFragment(NotificationFragment())
    }

    private fun perFormClicks() {


    }

    /*Call back for changing the title of toolbar*/

    public fun setOnTitlechangeListener(listenre: TitleChangeListenre){
        titleChangeListenre = listenre
    }
    public fun setOnBottomNavClickListener(listenre: BottomNavClickListenre){
        navClickListenre = listenre
    }
    override fun onClick(p0: View?) {
        when (p0?.id) {


        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.home -> {
                replaceFragment(NotificationFragment())
                navClickListenre!!.onBottonNavClick(false)
                return true
            }
            R.id.messages -> {
                replaceFragment(MessagesFragment())
                navClickListenre!!.onBottonNavClick(true)
                callListener("Messages")
                return true
            }
            R.id.notices -> {
                navClickListenre!!.onBottonNavClick(false)
                replaceFragment(NoticesFragment())
                callListener("Notices")
                return true
            }
            R.id.tenants -> {
                navClickListenre!!.onBottonNavClick(false)
                replaceFragment(TenantsFragment())
                callListener("Tenants")
                return true
            }


        }
        return false
    }

    fun callListener(text:String){
        titleChangeListenre!!.onTitleChange(text)
    }

    private fun replaceFragment(fragment: Fragment?) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (fragment != null) {
            fragmentTransaction.replace(R.id.homeFragmentFrame, fragment).commit()
        }


    }
}