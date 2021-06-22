package com.app.landlord.modules.home.dashBoard.home.fragments.notices.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.landlord.R
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.model.Data
import com.app.landlord.databinding.FragmentCurrentBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.adapter.GenrallNoticesAdapter

import com.app.landlord.modules.home.dashBoard.home.fragments.notices.adapter.LegalNoticesAdapter
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.bottomSheet.BottomSheetFragment

class CurrentFragment : Fragment(), View.OnClickListener {
    private val TAG: String? = "CurrentFragment"
    val list = ArrayList<Data>()
    var binding: FragmentCurrentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (binding == null) {
            binding = FragmentCurrentBinding.inflate(inflater, container, false)
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perFormClicks()
        setUpLegalRv()
        setUpGeneralRv()
    }

    private fun setUpGeneralRv() {
        binding!!.recyclerViewGeneral.layoutManager = LinearLayoutManager(activity)
        binding!!.recyclerViewGeneral.adapter = activity?.let { GenrallNoticesAdapter(it, list) }

    }

    private fun setUpLegalRv() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding!!.recyclerView.adapter = activity?.let { LegalNoticesAdapter(it, list) }
    }


    private fun perFormClicks() {
        binding!!.floatingButton.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.floatingButton -> {
//                val view = activity?.layoutInflater?.inflate(R.layout.fragment_bottom_sheet_dialog,null)
//                val dialog  = activity?.let { BottomSheetDialog(it) }
//                if (view != null) {
//                    dialog?.setContentView(view)
//                    dialog?.show()
                val bottomSheet = BottomSheetFragment()
                activity?.supportFragmentManager?.let {
                    bottomSheet.show(
                        it,
                        bottomSheet.tag
                    )
                }

            }
        }

    }


}