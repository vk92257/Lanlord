package com.app.landlord.modules.home.dashBoard.home.fragments.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.app.landlord.databinding.FragmentHomeBinding
import com.app.landlord.databinding.FragmentMessagesBinding
import com.app.landlord.databinding.FragmentNotificationsBinding

class MessagesFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "MessagesFragment"

    var binding: FragmentMessagesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentMessagesBinding.inflate(inflater, container, false)
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()

    }


    private fun perFormClicks() {


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {


        }
    }


}