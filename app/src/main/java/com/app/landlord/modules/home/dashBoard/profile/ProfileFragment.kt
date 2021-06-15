package com.app.landlord.modules.home.dashBoard.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.landlord.R
import com.app.landlord.databinding.FragmentHomeBinding
import com.app.landlord.databinding.FragmentProfileBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.messages.MessagesFragment
import com.app.landlord.modules.home.dashBoard.home.fragments.notifications.NotificationFragment
import com.app.landlord.modules.home.dashBoard.home.listener.TitleChangeListenre
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "ProfileFragment"

    var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentProfileBinding.inflate(inflater, container, false)
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()
    }

    private fun perFormClicks() {

        binding!!.root.btnUpdateProfile.setOnClickListener(this)
        binding!!.root.profile_image.setOnClickListener(this)
        binding!!.root.profileLocation.setOnClickListener(this)
        binding!!.root.profileProvinceIcon.setOnClickListener(this)
        binding!!.root.profileCountryIcon.setOnClickListener(this)

    }



    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnUpdateProfile->{
                verifyUserInfo()
            }
            R.id.profile_image->{
                verifyUserInfo()
            }
            R.id.profileLocation->{verifyUserInfo()
            }
            R.id.profileProvinceIcon->{verifyUserInfo()

            } R.id.profileCountryIcon->{verifyUserInfo()

        }

        }
    }

    private fun verifyUserInfo() {
        Log.e(TAG, "verifyUserInfo: " )
        if ( binding!!.root.profile_mobile_Number.text.toString().isNullOrEmpty()){
            binding!!.root.profile_mobile_Number.setError("Please enter your  Phone Number")
            binding!!.root.profile_mobile_Number.requestFocus()
        } else if (binding!!.root.profileHomePhoneNumber.text.toString().isNullOrEmpty()) {
            binding!!.root.profileHomePhoneNumber.setError("Please enter your Home Phone Number")
            binding!!.root.profileHomePhoneNumber.requestFocus()
        }  else if (binding!!.root.profileBusinessName.text.toString().isNullOrEmpty()) {
            binding!!.root.profileBusinessName.setError("Please enter your Business Name")
            binding!!.root.profileBusinessName.requestFocus()
        } else if (binding!!.root.profileBusinessPhoneNumber.text.toString().isNullOrEmpty()) {
            binding!!.root.profileBusinessPhoneNumber.setError("Please enter your Business Phone Number")
            binding!!.root.profileBusinessPhoneNumber.requestFocus()
        } else if (binding!!.root.profileBusinessWebsite.text.toString().isNullOrEmpty()) {
            binding!!.root.profileBusinessWebsite.setError("Please enter your Business Website")
            binding!!.root.profileBusinessWebsite.requestFocus()
        } else if (binding!!.root.profileStreenName.text.toString().isNullOrEmpty()) {
            binding!!.root.profileStreenName.setError("Please enter your Street Name")
            binding!!.root.profileStreenName.requestFocus()
        } else if (binding!!.root.profileProvince.text.toString().isNullOrEmpty()) {
            binding!!.root.profileProvince.setError("Please select your province")
            binding!!.root.profileProvince.requestFocus()
        } else if (binding!!.root.profilePostalCode.text.toString().isNullOrEmpty()) {
            binding!!.root.profilePostalCode.setError("Please enter your Postal Code")
            binding!!.root.profilePostalCode.requestFocus()
        } else if (binding!!.root.profileCountry.text.toString().isNullOrEmpty()) {
            binding!!.root.profileCountry.setError("Please select your country")
            binding!!.root.profileCountry.requestFocus()


        }

    }
}