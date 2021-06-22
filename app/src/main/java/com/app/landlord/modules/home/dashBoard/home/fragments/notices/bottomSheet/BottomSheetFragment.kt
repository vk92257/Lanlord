package com.app.landlord.modules.home.dashBoard.home.fragments.notices.bottomSheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.landlord.R
import com.app.landlord.databinding.FragmentBottomSheetDialogBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.legalNotice.LegalNoticeActivity
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.selectTenants.SelectTenants
import com.app.landlord.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment(),View.OnClickListener{
    var binding : FragmentBottomSheetDialogBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)

    }

    private fun perFormClicks() {
        binding!!.generNotices.setOnClickListener(this)
        binding!!.dismiss.setOnClickListener(this)
        binding!!.legalNotice.setOnClickListener(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null){
            binding = FragmentBottomSheetDialogBinding.inflate(inflater,container,false)
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.generNotices ->{
                Log.e("TAG", "onClick: ")
                startActivity(Intent(activity,SelectTenants::class.java).putExtra(Constants.TYPE,Constants.GENERAL_NOTICE))
                dismiss()
            }

            R.id.dismiss ->{
                Log.e("TAG", "onClick: ")
                   dismiss()
            }

            R.id.legalNotice ->{
                Log.e("TAG", "onClick: ")
                startActivity(Intent(activity,SelectTenants::class.java).putExtra(Constants.TYPE,Constants.LEAGAL_NOTICE))
                dismiss()
            }
        }
    }
}