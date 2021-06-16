package com.app.landlord.modules.home.dashBoard.maintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.landlord.databinding.*
import com.app.landlord.modules.home.dashBoard.maintenance.adapter.MaintenanceAdapter
import com.app.landlord.modules.home.dashBoard.maintenance.model.Data

class MaintenanceFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "MessagesFragment"
    val list = ArrayList<Data>()
     var  adapter:MaintenanceAdapter?= null
    var binding: FragmentMaintenanceBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentMaintenanceBinding.inflate(inflater, container, false)
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
        adapter = context?.let { MaintenanceAdapter(it,list) }
        binding!!.rvOpen.adapter = adapter
    }
    private fun perFormClicks() {


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {


        }
    }


}