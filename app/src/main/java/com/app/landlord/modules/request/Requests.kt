package com.app.landlord.modules.request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.landlord.R
import com.app.landlord.databinding.ActivityRequestsBinding
import com.app.landlord.modules.request.adapter.RequestImageAdapter
class Requests : AppCompatActivity(), View.OnClickListener {
  var binding: ActivityRequestsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if ( binding == null){
            binding = ActivityRequestsBinding.inflate(layoutInflater)
        }
        setContentView(binding!!.root)
        setupRv()
        performClick()
    }

    private fun performClick() {
        binding!!.btnCompleteRequest.setOnClickListener(this)
        binding!!.reject.setOnClickListener(this)
        binding!!.accept.setOnClickListener(this)
        binding!!.back.setOnClickListener(this)
    }

    private fun setupRv() {
        binding!!.rvRequestImg.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding!!.rvRequestImg.adapter = RequestImageAdapter()

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnCompleteRequest ->{
                    finish()
            }
            R.id.reject ->{
                binding!!.bottomBtns.visibility = View.GONE
                binding!!.btnCompleteRequest.visibility = View.VISIBLE
            }
            R.id.accept ->{
                binding!!.bottomBtns.visibility = View.GONE
                binding!!.btnCompleteRequest.visibility = View.VISIBLE
            }
            R.id.back ->{
                finish()
             }

        }
    }
}