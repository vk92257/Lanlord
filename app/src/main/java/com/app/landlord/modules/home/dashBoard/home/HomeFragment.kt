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
import com.app.landlord.modules.home.dashBoard.home.fragments.notifications.MessagesFragment
import com.app.landlord.modules.home.dashBoard.home.fragments.notifications.NotificationFragment
import com.app.landlord.modules.home.dashBoard.home.listener.TitleChangeListenre
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    private val TAG: String? = "HomeFragment"
    var titleChangeListenre: TitleChangeListenre?= null
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

   public fun setOnTitlechangeListener(listenre: TitleChangeListenre){
        titleChangeListenre = listenre
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
                return true
            }
            R.id.messages -> {
                replaceFragment(MessagesFragment())
                callListener("Messages")
                return true
            }
            R.id.notices -> {
                callListener("Notices")
                replaceFragment(NotificationFragment())
                return true
            }
            R.id.tenants -> {
                callListener("Tenants")
                replaceFragment(NotificationFragment())
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