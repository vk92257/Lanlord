package com.app.landlord.modules.home.dashBoard.home.fragments.notices.adapter

import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.landlord.modules.home.dashBoard.home.fragments.messages.MessagesFragment
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.current.CurrentFragment
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.past.PastFragment
import com.app.landlord.modules.home.dashBoard.profile.ProfileFragment


class ViewPagerAdapter(
    fm: FragmentManager,
    l: Lifecycle
) : FragmentStateAdapter(fm, l) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 ->  return CurrentFragment()
            1 -> return PastFragment()
            else ->  return CurrentFragment()
        }
    }
}
