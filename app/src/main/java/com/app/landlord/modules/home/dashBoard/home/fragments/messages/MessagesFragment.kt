package com.app.landlord.modules.home.dashBoard.home.fragments.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.landlord.databinding.FragmentHomeBinding
import com.app.landlord.databinding.FragmentMessagesBinding
import com.app.landlord.databinding.FragmentNotificationsBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.messages.adapter.UsersAdapter
import com.app.landlord.modules.home.dashBoard.home.fragments.messages.model.Data

class MessagesFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "MessagesFragment"

    var binding: FragmentMessagesBinding? = null
    val list = ArrayList<Data>()
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
        setUpRV()
    }

    private fun setUpRV() {
        binding!!.rvUsers.layoutManager = LinearLayoutManager(activity)
        binding!!.rvUsers.adapter = context?.let { UsersAdapter(it,list) }
        binding!!.rvUsers.setHasFixedSize(true)

    }


    private fun perFormClicks() {


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {


        }
    }


}