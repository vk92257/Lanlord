package com.app.landlord.modules.home.dashBoard.home.fragments.notices.noticeDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.app.landlord.databinding.ActivityNoticeDetailBinding
import com.app.landlord.utils.Constants

class NoticeDetailActivity : AppCompatActivity() {
    var binding: ActivityNoticeDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) {
            binding = ActivityNoticeDetailBinding.inflate(layoutInflater)
        }
        setContentView(binding!!.root)

        val type = intent.getStringExtra(Constants.TYPE)
        Log.e("TAG", "onCreate: "+type )
        binding!!.heading.setText(type)

        if (type.equals(Constants.GENERAL_NOTICE)) {
            binding!!.general.visibility = View.VISIBLE
        } else if (type.equals(Constants.LEAGAL_NOTICE)) {
            binding!!.legal.visibility = View.VISIBLE

        }

    }
}