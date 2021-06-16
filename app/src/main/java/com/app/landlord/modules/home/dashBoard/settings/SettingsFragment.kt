package com.app.landlord.modules.home.dashBoard.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.landlord.R
import  com.app.landlord.databinding.FragmentSettingsBinding
import com.app.landlord.modules.changePassword.ChangePasswordActivity

import kotlinx.android.synthetic.main.fragment_profile.view.*


class SettingsFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "SettingsFragment"

    var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding =  FragmentSettingsBinding.inflate(inflater, container, false)
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()
    }


    private fun perFormClicks() {
        binding!!.changePassword.setOnClickListener(this)


    }



    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.changePassword ->{
                startActivity(Intent(activity,ChangePasswordActivity::class.java))
            }
        }

        }
    }


