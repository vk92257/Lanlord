package com.app.landlord.modules.home.dashBoard.requestAndProblems.complete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.landlord.databinding.*

import com.app.landlord.modules.home.dashBoard.requestAndProblems.complete.adapter.ContinueAdapter
import com.app.landlord.modules.home.dashBoard.requestAndProblems.complete.model.Data

class CompleteFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "MessagesFragment"
    val list = ArrayList<Data>()
    var  adapter:ContinueAdapter?= null
    var binding: FragmentCompleteBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentCompleteBinding.inflate(inflater, container, false)
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()
        settingUpRv()
    }

    private fun settingUpRv() {
        binding!!.rvOpen.layoutManager = LinearLayoutManager(activity)
        adapter = context?.let { ContinueAdapter(it,list) }
        binding!!.rvOpen.adapter = adapter
    }
    private fun perFormClicks() {


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {


        }
    }


}