package com.app.landlord.modules.home.dashBoard.home.fragments.notices.selectTenants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.landlord.R

import com.app.landlord.databinding.ActivitySelectTenantsBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.messages.chat.model.Data
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.legalNotice.LegalNoticeActivity
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.selectTenants.adaptaer.TenantsAdapter
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.selectTenants.listener.OnclickListener
import com.app.landlord.utils.Constants

class SelectTenants : AppCompatActivity(), View.OnClickListener, OnclickListener {
    var binding: ActivitySelectTenantsBinding? = null
    val list = ArrayList<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) {
            binding = ActivitySelectTenantsBinding.inflate(layoutInflater)
        }
        setContentView(binding!!.root)
        performOnClick()
        setUpRV()
    }


    private fun performOnClick() {
        binding!!.back.setOnClickListener(this)
    }

    private fun setUpRV() {
        binding!!.rvUsers.layoutManager = LinearLayoutManager(this)

       val adapter  =  TenantsAdapter(this, list)
        binding!!.rvUsers.adapter = adapter
            adapter.addOnClickListener(this)
        binding!!.rvUsers.setHasFixedSize(true)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.back -> {
                finish()
            }
        }
    }

    override fun onClick() {

        val type: String? = intent.getStringExtra(Constants.TYPE)
        startActivity(Intent(this, LegalNoticeActivity::class.java).putExtra(Constants.TYPE, type))
        finish()

    }
}