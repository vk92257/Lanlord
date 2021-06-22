package com.app.landlord.modules.home.dashBoard.home.fragments.notices.legalNotice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import com.app.landlord.R
import com.app.landlord.databinding.ActivityLegalNoticeBinding
import com.app.landlord.utils.Constants

class LegalNoticeActivity : AppCompatActivity() {
    var binding: ActivityLegalNoticeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) {
            binding = ActivityLegalNoticeBinding.inflate(layoutInflater)
        }
        setContentView(binding!!.root)
//            setContentView(R.layout.activity_legal_notice)

        Log.e("TAG", "onCreate: "+intent.getStringExtra(Constants.TYPE))
        binding!!.heading.setText(intent.getStringExtra(Constants.TYPE))

//        if (intent.getStringExtra(Constants.TYPE).equals(Constants.LEAGAL_NOTICE)) {
//            binding!!.heading.setText("Leagal Notice")
//
//        } else if (intent.getStringExtra(Constants.TYPE).equals(Constants.GENERAL_NOTICE)) {
//            binding!!.heading.setText("General Notice")
//
//        }
    }


}