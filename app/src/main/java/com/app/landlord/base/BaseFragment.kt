package com.app.landlord.base


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.landlord.utils.AppUtils
import com.app.landlord.utils.DialogsUtil

abstract class BaseFragment : Fragment() {
    var mAppUtils: AppUtils? = null
    var mDialogsUtil: DialogsUtil? = null

    var baseActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        mAppUtils = AppUtils(requireActivity())
        mDialogsUtil = DialogsUtil(requireActivity())

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity)
            baseActivity = context
    }

    fun showToast(msg: String) {
        baseActivity!!.showToast(msg)
    }

    fun showFragment(tag: String, container: Int, bundle: Bundle? = null) {
        baseActivity!!.showFragment(tag, container, bundle)
    }

    fun showFragmentWithoutTrans(tag: String, container: Int, bundle: Bundle? = null) {
        baseActivity!!.showFragmentWithoutTrans(tag, container, bundle)
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }


}
