package com.app.landlord.modules.home.dashBoard.feedback

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.landlord.databinding.FragmentHelpSupportBinding
import com.app.landlord.databinding.FragmentHelpSupportBindingImpl
import  com.app.landlord.databinding.FragmentSettingsBinding
import com.app.landlord.modules.home.dashBoard.feedback.listener.OnCancelClikcListener
import com.app.landlord.utils.FragConst

import kotlinx.android.synthetic.main.fragment_profile.view.*


class FeedBackFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "SettingsFragment"

    private val reciever = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d("receiver", "Cancel Click is working " )
        }

    }
    var binding: FragmentHelpSupportBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding =  FragmentHelpSupportBinding.inflate(inflater, container, false)
        }





        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { LocalBroadcastManager.getInstance(it).registerReceiver(reciever,
            IntentFilter( FragConst.LocalBroadCasst.CANCEL_CLICK)) }
    }

    override fun onDestroy() {
        activity?.let { LocalBroadcastManager.getInstance(it).unregisterReceiver(reciever) }
        super.onDestroy()

    }


    override fun onClick(p0: View?) {
        when (p0?.id) {

        }

        }
    }


