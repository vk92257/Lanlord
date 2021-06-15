package com.app.landlord.modules.home.dashBoard.home.fragments.notices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

import com.app.landlord.databinding.FragmentHomeBinding
import com.app.landlord.databinding.FragmentMessagesBinding
import com.app.landlord.databinding.FragmentNoticesBinding
import com.app.landlord.databinding.FragmentNotificationsBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_notices.*

class NoticesFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "MessagesFragment"

    var binding: FragmentNoticesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentNoticesBinding.inflate(inflater, container, false)
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        var fm = activity?.supportFragmentManager
        var adapter = fm?.let { ViewPagerAdapter(it,lifecycle) }
        binding!!.tabViewpager.adapter= adapter
        binding!!.tabTablayout.addTab( binding!!.tabTablayout.newTab().setText("Current"))
        binding!!.tabTablayout.addTab( binding!!.tabTablayout.newTab().setText("Past"))

        binding!!.tabTablayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener  {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding!!.tabViewpager.setCurrentItem(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        } )
        binding!!.tabViewpager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding!!.tabTablayout.selectTab( binding!!.tabTablayout.getTabAt(position))

            }
            })
    }


    private fun perFormClicks() {


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {


        }
    }


}